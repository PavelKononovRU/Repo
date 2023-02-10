package com.exchangeinformant.subscription.controller;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import com.exchangeinformant.subscription.model.Subscription;
import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.util.enums.Interval;
import com.exchangeinformant.subscription.util.enums.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/data.sql")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AdminRestSubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllSubscriptionsTest() throws Exception {
        mockMvc.perform(get("/api/subscriptions")
                        .with(user("admin").password("admin").roles("USER","ADMIN")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createSubscriptionTest() throws Exception {
        Tariff tariff = Tariff.builder().id(3l).title("title").type("type").isActive(true).build();
        Subscription sub = Subscription.builder().id(3L).tariff(tariff)
                .createdAt(LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30,10))
                .updatedAt(LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30,10))
                .startAt(LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30,10))
                .expiresAt(LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30,10))
                .status(Status.ACTIVE).interval(Interval.DAY)
                .intervalCount(3).price(3).sendSMS(3).userId(3L)
                .isPromo(true).build();
        this.mockMvc.perform(post("/api/subscriptions").with(csrf())
                        .with(user("admin").password("admin").roles("USER","ADMIN"))
                        .content(objectMapper.writeValueAsString(sub))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
    @Test
    void updateSubscriptionTest() throws Exception {
        Tariff tariff = Tariff.builder().id(1l).title("title2").type("type2").isActive(true).build();
        Subscription sub = Subscription.builder().id(1L).tariff(tariff)
                .createdAt(LocalDateTime.of(2017, Month.FEBRUARY, 20, 06, 30,10))
                .updatedAt(LocalDateTime.of(2017, Month.FEBRUARY, 20, 06, 30,10))
                .startAt(LocalDateTime.of(2017, Month.FEBRUARY, 20, 06, 30,10))
                .expiresAt(LocalDateTime.of(2017, Month.FEBRUARY, 20, 06, 30,10))
                .status(Status.ACTIVE).interval(Interval.DAY)
                .intervalCount(3).price(3).sendSMS(3).userId(3L)
                .isPromo(true).build();
        this.mockMvc.perform(put("/api/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sub))
                        .with(csrf())
                        .with(user("admin").password("admin").roles("USER","ADMIN")))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getSubscriptionByIdTest() throws Exception {
        Long id = 2L;
        mockMvc.perform(get("/api/subscriptions/{id}", id)
                        .with(user("admin").password("admin").roles("USER","ADMIN")))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void getSubscriptionByStatusTest() throws Exception {
        String status = "ACTIVE";
        int offset = 0;
        int limit = 1;
        this.mockMvc.perform(get("/api/get-subscriptions-by-status?status={status}&&offset={offset}&&limit={limit}",
                        status, offset, limit)
                        .with(user("admin").password("admin").roles("USER","ADMIN")))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void deleteSubscriptionTest() throws Exception {
        Long id = 2L;
        this.mockMvc.perform(delete("/api/subscriptions/{id}", id).with(csrf())
                        .with(user("admin").password("admin").roles("USER","ADMIN")))
                .andDo(print())
                .andExpect(status().isOk());
    }
}