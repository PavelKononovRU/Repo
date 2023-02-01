package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.dto.StubPaymentDTO;
import com.exchange.payingservice.entity.Card;
import com.exchange.payingservice.entity.Payment;
import com.exchange.payingservice.exceptions.PaymentException;
import com.exchange.payingservice.mappers.PaymentsMapper;
import com.exchange.payingservice.repository.PaymentRepository;
import com.exchange.payingservice.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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
    private static int flag;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentsMapper paymentsMapper, CardService cardService) {
        this.paymentRepository = paymentRepository;
        this.paymentsMapper = paymentsMapper;
        this.cardService = cardService;
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    @Override
    @Transactional
    public StubPaymentDTO createPayment(StubPaymentDTO payment, Status status) {
        Card card = cardService.getCardById(payment.getCard_id());
        Payment created = new Payment();
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
    public ResponseEntity<Object> methodGetBodyToStubPayment(StubPaymentDTO stubPayment) {
        Status status = Status.SUCCESSFULLY;
        String extId = "1-" +
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-" + ++flag;
        Map<String, String> testMap = new HashMap<>();
        testMap.put("ext_id", extId);
        testMap.put("amount", stubPayment.getItems().get("amount"));

        RestTemplate restTemplateStubPayment = new RestTemplate();

        ResponseEntity<Object> response;
        try {
            response = restTemplateStubPayment.postForEntity("http://localhost:8082/stub/payment", testMap, Object.class);
        } catch (HttpClientErrorException.UnprocessableEntity e) {
            status = Status.ERROR;
            throw new PaymentException("Платеж не прошел,пожалуйста,повторите позже.");
        } finally {
            this.createPayment(stubPayment, status);
        }
        return response;
    }

    @Scheduled(cron = "* * 1 * * ?")
    protected void clearCounter() {
        flag = 0;
    }

}
