package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.entity.UserInfo;
import com.exchangeinformat.userprofile.service.UserInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class RestUserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserInfoService userInfoService;

    @InjectMocks
    private RestUserController restUserController;

    private UserInfo userInfo;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(restUserController).build();

        userInfo = new UserInfo("d0755b35-d79f-4a83-9275-7af8000372f40", 10,
                LocalDateTime.of(2023, 02, 02, 12, 00, 00),
                LocalDateTime.of(2023, 02, 02, 12, 30, 00),
                "bcs");
    }

    @Test
    void shouldGetInfo() throws Exception {

        when(userInfoService.getById(Mockito.any())).thenReturn(userInfo);
        System.out.println(userInfoService.getById("swswws"));
        System.out.println(userInfo);

        mockMvc.perform(get("/getInfo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId", is("d0755b35-d79f-4a83-9275-7af8000372f40")))
                .andExpect(jsonPath("$.counter", is(10)))
                .andExpect(jsonPath("$").isNotEmpty());
    }
}