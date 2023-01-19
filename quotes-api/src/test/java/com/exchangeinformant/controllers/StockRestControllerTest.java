package com.exchangeinformant.controllers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StockRestController.class)
class StockRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getStock() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock?AAPL");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals("Apple", result.getResponse().getContentAsString());
    }

    @Test
    @Disabled
    void getAllStocksByDates() {
    }
}