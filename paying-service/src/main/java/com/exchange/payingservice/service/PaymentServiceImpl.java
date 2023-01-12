package com.exchange.payingservice.service;

import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.dto.StudPaymentDTO;
import com.exchange.payingservice.mappers.CardMapper;
import com.exchange.payingservice.mappers.PaymentsMapper;
import com.exchange.payingservice.repository.PaymentRepository;
import com.exchange.payingservice.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private ResponseEntity<Object> response;

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
    public StudPaymentDTO createPayment(StudPaymentDTO payment, Payment.Status status) {
        PaymentDTO created = new PaymentDTO();
        created.setCard((cardService.getCardById(payment.getCard_id())));
        created.setCreateAt(new Date());
        created.setUpdateAt(new Date());
        created.setStatus(status);
        created.setMessage("MESSAGE");
        created.setUser_id(666L);
        System.out.println(payment);
        paymentRepository.save(paymentsMapper.toEntity(created));
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

    // Имитирует тело для POST запроса
    @Override
    public StudPaymentDTO testMethodPostToStudPayment() {
        StudPaymentDTO studPayment = new StudPaymentDTO();
        studPayment.setPhone("string");
        studPayment.setEmail("ivanov@gmail.com");
        studPayment.setPromocode("PROMO412GWOT");
        studPayment.setCard_id(64556L);
        Map<String, String> mapItems = new HashMap<>();
        mapItems.put("amount", "129900");
        mapItems.put("subscription_id", "12345");
        studPayment.setItems(mapItems);
        return studPayment;
    }

    @Override
    public ResponseEntity<Object> methodGetBodyToStudPayment(StudPaymentDTO studPayment) {
        String stringBuilder = "1-" +
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-" + ++flag;
        Map<String, String> testMap = studPayment.getItems();
        testMap.put("ext_id", stringBuilder);
        testMap.put("status", "SUCCESSFULLY");
        testMap.put("createAt", LocalDateTime.now().toString());

        RestTemplate restTemplateStudPayment = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response = restTemplateStudPayment.postForEntity(
                "http://localhost:8082/stud/payment/v1", testMap, Object.class);
        return response;
    }

}
