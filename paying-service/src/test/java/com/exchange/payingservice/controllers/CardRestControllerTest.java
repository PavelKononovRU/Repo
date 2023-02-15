package com.exchange.payingservice.controllers;

import com.exchange.payingservice.IntegrationTestBase;
import com.exchange.payingservice.dto.CardDTO;
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
    private MockMvc mockMvc;


    @Test
    @DisplayName("Should get card by id")
    void shouldGetCard() throws Exception {
        mockMvc.perform(get("/api/cards/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should create new card")
    void shouldSaveCard() throws Exception {
        mockMvc.perform(post("/api/cards")
                        .content(objectMapper
                                .writeValueAsString(
                                        new CardDTO(
                                                "2103-1122-2323-2323",
                                                "user55",
                                                "754",
                                                3L)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should not create card with same number")
    void shouldNotSaveCard() throws Exception {
        CardDTO cardDTO = new CardDTO("2101-2222-3333-4444", "user55", "754", 3L);
        mockMvc.perform(post("/api/cards")
                        .content(objectMapper.writeValueAsString(cardDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.title").value("Данный номер карты уже существует"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Should get all card")
    void shouldGetAllCards() throws Exception {
        mockMvc.perform(get("/api/cards"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should delete card by id")
    void shouldDeleteCard() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cards/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should update card by id")
    void shouldUpdateCard() throws Exception {
        CardDTO cardDTO = new CardDTO(2L, "2011-2222-3333-3333", "user3", "777", 2L);
        mockMvc.perform(put("/api/cards/{id}", 2L)
                        .content(objectMapper.writeValueAsString(cardDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Should not found card by wrong id")
    void shouldGetIdNotFound() throws Exception {
        mockMvc.perform(get("/api/cards/{id}", 10))
                .andDo(print())
                .andExpect(jsonPath("$.title").value("Карта с номером 10 не найдена"))
                .andExpect(status().is4xxClientError());
    }

}