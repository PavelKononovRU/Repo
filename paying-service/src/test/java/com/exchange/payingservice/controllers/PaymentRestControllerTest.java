package com.exchange.payingservice.controllers;

import com.exchange.payingservice.IntegrationTestBase;
import com.exchange.payingservice.dto.StudPaymentDTO;
import com.exchange.payingservice.entity.Card;
import com.exchange.payingservice.entity.Payment;
import com.exchange.payingservice.util.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(Runner.class)
class PaymentRestControllerTest extends IntegrationTestBase {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("get payment by id")
    void getPayment() throws Exception {
        mockMvc.perform(get("/api/payments/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("get all payment")
    void getAllPayments() throws Exception {
        mockMvc.perform(get("/api/payments"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("update payment")
    void updatePayment() throws Exception {
        Card card = new Card("1111-2222-3333-4567", "user5", "515", 6L);
        mockMvc.perform(put("/api/payments/{id}", 1L)
                        .content(objectMapper.writeValueAsString
                                (new Payment(1L,
                                        card,
                                        new Date(),
                                        new Date(),
                                        6L,
                                        Status.SUCCESSFULLY,
                                        "MESSAGE")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("delete payment")
    void deletePayment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/payments/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("save status ERROR payment")
    void createErrorPayment() {
        StudPaymentDTO studPaymentDTO = createStudPaymentDTO();
        assertEquals(Status.ERROR, createTestPaymentStubError(studPaymentDTO).getBody());
    }

    @Test
    @DisplayName("get payment by id not found")
    void getPaymentNotFound() throws Exception {
        mockMvc.perform(get("/api/payments/{id}", 10))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("save status SUCCESSFULLY payment")
    void createSuccessfulyPayment() {
        StudPaymentDTO studPaymentDTO = createStudPaymentDTO();
        assertEquals(Status.SUCCESSFULLY, createTestPaymentStubSuccessfuly(studPaymentDTO).getBody());
    }

    private ResponseEntity createTestPaymentStubError(StudPaymentDTO studPaymentDTO) {
        return new ResponseEntity<>(Status.ERROR, HttpStatus.OK);
    }

    private ResponseEntity createTestPaymentStubSuccessfuly(StudPaymentDTO studPaymentDTO) {
        return new ResponseEntity<>(Status.SUCCESSFULLY, HttpStatus.OK);
    }

    private StudPaymentDTO createStudPaymentDTO() {
        Map<String, String> items = new HashMap<>();
        items.put("subscription_id", "12345");
        items.put("amount", "129900");

        StudPaymentDTO studPaymentDTO = new StudPaymentDTO();
        studPaymentDTO.setPromocode("PROMO412GWOT");
        studPaymentDTO.setEmail("test@mail.ru");
        studPaymentDTO.setItems(items);
        studPaymentDTO.setCard_id(1L);
        studPaymentDTO.setPhone("string");
        return studPaymentDTO;
    }

}