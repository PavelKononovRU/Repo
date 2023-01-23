package com.exchange.payingservice.service;

import com.exchange.payingservice.IntegrationTestBase;
import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.dto.StudPaymentDTO;
import com.exchange.payingservice.entity.Card;
import com.exchange.payingservice.entity.Payment;
import com.exchange.payingservice.mappers.PaymentsMapper;
import com.exchange.payingservice.util.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceTest extends IntegrationTestBase {

    @Autowired
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void testGetById() {
        Optional<Payment> maybePayment = Optional.ofNullable(paymentService.getPaymentById(1L));
        assertTrue(maybePayment.isPresent());
        maybePayment.ifPresent(entity -> {
            assertEquals(maybePayment.get(), entity);
        });
    }


    @Test
    void testSave() {
        Map<String, String> testItemsMap = new HashMap<>();
        testItemsMap.put("ext_id", "1-");
        testItemsMap.put("amount", "1000");

        StudPaymentDTO studPaymentDTO = new StudPaymentDTO();
        studPaymentDTO.setCard_id(1L);
        studPaymentDTO.setEmail("test@mail.ru");
        studPaymentDTO.setItems(testItemsMap);
        studPaymentDTO.setPhone("79876543210");
        studPaymentDTO.setPromocode("PROMO412GWOT");

        paymentService.createPayment(studPaymentDTO, Status.SUCCESSFULLY);

        boolean b = false;
        for (Payment em : paymentService.getAllPayment()) {
            if (em.getStatus().equals(Status.SUCCESSFULLY)) {
                b = true;
                break;
            }
        }
        assertTrue(b);
    }

    @Test
    void testDelete() {
        paymentService.deletePaymentById(1L);
        Optional<Payment> maybeCard = Optional.ofNullable(paymentService.getPaymentById(1L));
        maybeCard.ifPresent(entity -> {
            assertNull(entity.getStatus());
        });
    }

    @Test
    void testUpdate() {
        PaymentDTO paymentDTO = PaymentsMapper.INSTANCE.toDTO(paymentService.getPaymentById(1L));
        paymentDTO.setUpdateAt(new Date());
        paymentDTO.setCreateAt(new Date());
        paymentDTO.setUser_id(2L);
        paymentDTO.setCard(new Card("22222-3333-4444-5555", "user55", "654", 2L));
        paymentDTO.setMessage("UPDATE");
        paymentService.updatePayment(paymentDTO.getId(), paymentDTO);
        assertEquals("UPDATE", paymentService.getPaymentById(1L).getMessage());
        assertNotNull(paymentDTO.getId());
    }

}
