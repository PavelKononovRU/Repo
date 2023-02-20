package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.dto.StubPaymentDTO;
import com.exchange.payingservice.entity.Card;
import com.exchange.payingservice.entity.Payment;
import com.exchange.payingservice.exceptions.PaymentException;
import com.exchange.payingservice.mappers.PaymentsMapper;
import com.exchange.payingservice.repository.PaymentRepository;
import com.exchange.payingservice.util.rabbitMQ.PaymentProof;
import com.exchange.payingservice.util.PaymentStatus;
import com.exchange.payingservice.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentsMapper paymentsMapper;
    private final CardService cardService;
    private final StreamBridge streamBridge;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentsMapper paymentsMapper, CardService cardService, StreamBridge streamBridge) {
        this.paymentRepository = paymentRepository;
        this.paymentsMapper = paymentsMapper;
        this.cardService = cardService;
        this.streamBridge = streamBridge;
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<PaymentDTO> getAllPayment() {
        return paymentRepository.findAll().stream().map(paymentsMapper::toDTO).toList();
    }

    @Override
    @Transactional
    public StubPaymentDTO createPayment(StubPaymentDTO payment, Status status) {
        Card card = cardService.getCardById(payment.getCard_id());
        Payment created = new Payment();
        created.setCard(card);
        if (created.getCard() == null) throw new PaymentException("Карты с данным номером не обнаружено.");

        created.setCard(card);
        created.setCreateAt(new Date());
        created.setUpdateAt(new Date());
        created.setStatus(status);
        created.setMessage("MESSAGE");
        created.setUser_id(card.getUser_id());

        System.out.println(payment);
        paymentRepository.save(created);
        return payment;
    }

    @Override
    @Transactional
    public void updatePayment(Long id, PaymentDTO paymentDTO) {
        Payment paymentUp = paymentRepository.getReferenceById(id);
        paymentsMapper.updatePaymentFromDto(paymentDTO, paymentUp);
        paymentRepository.save(paymentUp);
    }

    @Override
    @Transactional
    public void deletePaymentById(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ResponseEntity<PaymentStatus> methodGetBodyToStubPayment(StubPaymentDTO stubPaymentDTO, Principal principal) {
        String extId = "1-" +
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-" + paymentRepository.getNextValue();
        Map<String, String> testMap = new HashMap<>();
        testMap.put("ext_id", extId);
        testMap.put("amount", stubPaymentDTO.getItems().get("amount"));

        RestTemplate restTemplateStubPayment = new RestTemplate();
        PaymentStatus paymentStatus = new PaymentStatus(Status.ERROR, "Ваш платеж не прошел, пожалуйста, повторите позже."); //
        ResponseEntity<PaymentStatus> response = new ResponseEntity<>(paymentStatus, HttpStatus.OK);

        try {
            response = restTemplateStubPayment.postForEntity("http://localhost:51861/stub/payment", testMap, PaymentStatus.class);
            paymentStatus.setStatus(Status.SUCCESSFULLY);

            //RabbitMQ toSubscription
            streamBridge.send("payment-proof",new PaymentProof(
                    Long.parseLong(stubPaymentDTO.getItems().get("subscription_id")),
                    stubPaymentDTO.getItems().get("amount"),
                    getExtID(principal)));
        } catch (PaymentException | HttpClientErrorException.UnprocessableEntity e) {
            paymentStatus.setStatus(Status.ERROR);//ignore Exception and save Error in DB
        }

        this.createPayment(stubPaymentDTO, paymentStatus.getStatus());
        return response;
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void reloadSequence() {
        paymentRepository.getNewSequence();
    }

    private String getExtID(Principal principal) {
        JwtAuthenticationToken kp = (JwtAuthenticationToken) principal;
        Jwt token = kp.getToken();
        Map<String, Object> userInfo = token.getClaims();
        return userInfo.get("sub").toString();
    }
}
