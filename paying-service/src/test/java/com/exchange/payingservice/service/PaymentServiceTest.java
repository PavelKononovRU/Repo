package com.exchange.payingservice.service;

import com.exchange.payingservice.IntegrationTestBase;
import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.dto.StubPaymentDTO;
import com.exchange.payingservice.entity.Payment;
import com.exchange.payingservice.exceptions.PaymentException;
import com.exchange.payingservice.mappers.PaymentsMapper;
import com.exchange.payingservice.util.Status;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest extends IntegrationTestBase {

    @Autowired
    private PaymentService paymentService;

    @Test
    @DisplayName("Should get payment by id")
    void shouldGetPaymentById() {
        Optional<Payment> maybePayment = Optional.ofNullable(paymentService.getPaymentById(1L));
        assertTrue(maybePayment.isPresent());
        maybePayment.ifPresent(entity -> assertEquals(maybePayment.get(), entity));
    }

    @Test
    @DisplayName("Should get all payments")
    void shouldGetAllPayments() {
        List<PaymentDTO> list = paymentService.getAllPayment();
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Should create payment by id")
    void shouldCreateNewPayment() {
        Map<String, String> testItemsMap = new HashMap<>();
        testItemsMap.put("ext_id", "1-");
        testItemsMap.put("amount", "1000");

        paymentService.createPayment(new StubPaymentDTO(
                        1L,
                        "79876543210",
                        "test@mail.ru",
                        testItemsMap,
                        "PROMO412GWOT"
                ),
                Status.SUCCESSFULLY);
        assertNotNull(paymentService.getPaymentById(3L));
    }

    @Test
    @DisplayName("Should not create payment by wrong card id")
    void shouldNotCreatePayment() {
        Map<String, String> testItemsMap = new HashMap<>();
        testItemsMap.put("ext_id", "1-");
        testItemsMap.put("amount", "1000");

        PaymentException exception = Assertions.assertThrows(PaymentException.class, () ->
                paymentService.createPayment(new StubPaymentDTO(
                                4L,
                                "79876543210",
                                "test@mail.ru",
                                testItemsMap,
                                "PROMO412GWOT"
                        ),
                        Status.SUCCESSFULLY));

        Assertions.assertEquals("Карта с номером 4 не найдена", exception.getMessage());
    }

    @Test
    @DisplayName("Should delete payment by id")
    void shouldDeletePaymentById() {
        paymentService.deletePaymentById(1L);
        Optional<Payment> maybeCard = Optional.ofNullable(paymentService.getPaymentById(1L));
        assertTrue(maybeCard.isEmpty());
    }

    @Test
    @DisplayName("Should update payment by id")
    void shouldUpdatePaymentById() {
        PaymentDTO paymentDTO = PaymentsMapper.INSTANCE.toDTO(paymentService.getPaymentById(1L));
        paymentDTO.setUpdateAt(new Date());
        paymentDTO.setCreateAt(new Date());
        paymentDTO.setUser_id(2L);
        paymentDTO.setCardDTO(new CardDTO("22222-3333-4444-5555", "user55", "654", 2L));
        paymentDTO.setMessage("UPDATE");
        paymentService.updatePayment(paymentDTO.getId(), paymentDTO);
        assertEquals("UPDATE", paymentService.getPaymentById(1L).getMessage());
        assertNotNull(paymentDTO.getId());
    }
}