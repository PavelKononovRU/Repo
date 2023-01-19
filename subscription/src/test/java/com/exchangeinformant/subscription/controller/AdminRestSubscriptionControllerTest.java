//package com.exchangeinformant.subscription.controller;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class AdminRestSubscriptionControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private Long id = 3L;
//
//    @Test
//    public void getAllSubscriptionsTest() throws Exception {
//        this.mockMvc.perform(get("/api/subscriptions"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void getSubscriptionByIdTest() throws Exception {
//        Long id = 1L;
//        this.mockMvc.perform(get("/api/subscriptions/{id}", id))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void getSubscriptionByStatusTest() throws Exception {
//        String status = "ACTIVE";
//        int offset = 0;
//        int limit = 1;
//        this.mockMvc.perform(get("/api/get-subscriptions-by-status?status={status}&&offset={offset}&&limit={limit}",
//                        status, offset, limit))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void createSubscriptionTest() throws Exception {
//
//        String requestBody = "{\n" +
//                "    \"tariff\": {\n" +
//                "        \"id\": 3,\n" +
//                "        \"title\": \"medium\",\n" +
//                "        \"description\": \"some_tariff_2\",\n" +
//                "        \"type\": \"test2\",\n" +
//                "        \"isActive\": true\n" +
//                "    },\n" +
//                "    \"createdAt\": \"2023-01-09T22:30:59\",\n" +
//                "    \"updatedAt\": \"2023-02-09T22:31:02\",\n" +
//                "    \"startAt\": \"2023-06-09T22:31:05\",\n" +
//                "    \"expiresAt\": \"2023-01-09T22:31:10\",\n" +
//                "    \"status\": \"AWAITING_TRANSACTION\",\n" +
//                "    \"interval\": \"TEST_2\",\n" +
//                "    \"intervalCount\": 789,\n" +
//                "    \"price\": 1200,\n" +
//                "    \"sendSMS\": 1,\n" +
//                "    \"userId\": 2\n" +
//                "}";
//        this.mockMvc.perform(post("/api/subscriptions")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(requestBody))
//                .andDo(print())
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void updateSubscriptionTest() throws Exception {
//        String body = "{\n" +
//                "    \"id\": 7,\n" +
//                "    \"tariff\": {\n" +
//                "        \"id\": 2,\n" +
//                "        \"title\": \"medium\",\n" +
//                "        \"description\": \"some_tariff_2\",\n" +
//                "        \"type\": \"test2\",\n" +
//                "        \"isActive\": false\n" +
//                "    },\n" +
//                "    \"createdAt\": \"2023-01-09T22:30:59\",\n" +
//                "    \"updatedAt\": \"2023-02-09T22:31:02\",\n" +
//                "    \"startAt\": \"2023-06-09T22:31:05\",\n" +
//                "    \"expiresAt\": \"2023-01-09T22:31:10\",\n" +
//                "    \"status\": \"ACTIVE\",\n" +
//                "    \"interval\": \"TEST_2\",\n" +
//                "    \"intervalCount\": 789,\n" +
//                "    \"price\": 40050,\n" +
//                "    \"sendSMS\": 1,\n" +
//                "    \"userId\": 2\n" +
//                "}";
//        this.mockMvc.perform(put("/api/subscriptions")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(body))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void deleteSubscriptionTest() throws Exception {
//        Long id = 7L;
//        this.mockMvc.perform(delete("/api/subscriptions/{id}", id))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//}
//
