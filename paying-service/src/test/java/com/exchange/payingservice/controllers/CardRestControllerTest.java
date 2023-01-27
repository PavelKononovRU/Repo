package com.exchange.payingservice.controllers;

import com.exchange.payingservice.IntegrationTestBase;
import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.entity.Card;
import com.exchange.payingservice.repository.CardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class CardRestControllerTest extends IntegrationTestBase {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("get card by id")
    void getCard() throws Exception {
        mockMvc.perform(get("/api/cards/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("create card")
    void saveCard() throws Exception {
        CardDTO cardDTO = new CardDTO("9999-4321-2323-2323", "user55", "754", 3L);
        mockMvc.perform(post("/api/cards")
                        .content(objectMapper.writeValueAsString(cardDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("get all card")
    void getAllCards() throws Exception {
        mockMvc.perform(get("/api/cards"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("delete card")
    void deleteCard() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cards/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("update")
    void updateCard() throws Exception {
        mockMvc.perform(put("/api/cards/{id}", 2L)
                        .content(objectMapper.writeValueAsString(new Card(2L, "1111-2222-3333-3333", "user3", "777", 2L)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("card not found")
    void cardGetIdNotFound() throws Exception {
        mockMvc.perform(get("/api/cards/{id}", 10))
                .andDo(print())
                .andExpect(jsonPath("$.title").value("Card not found"))
                .andExpect(status().is4xxClientError());
    }

}