package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.entity.User;
import com.exchangeinformat.userprofile.entity.UserInfo;
import com.exchangeinformat.userprofile.service.UserInfoService;
import com.exchangeinformat.userprofile.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ComponentScan(basePackages = "com.exchangeinformat.userprofile")
class RestUserControllerTest {
    private MockMvc mockMvc;
    @Mock
    private UserInfoService userInfoService;
    @Mock
    private UserService userService;
    @InjectMocks
    private RestUserController restUserController;

    private UserInfo userInfo;
    private User user;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(restUserController).build();
        userInfo = new UserInfo("d0755b35-d79f-4a83-9275-7af8000372f40", 10,
                LocalDateTime.of(2023, 2, 2, 12, 0, 0),
                LocalDateTime.of(2023, 2, 2, 12, 30, 0),
                "bcs");
        user = User.builder()
                .id(1L)
                .firstName("TestName")
                .username("TestUserName")
                .build();
    }

    @Test
    void shouldGetInfo() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        Mockito.when(mockPrincipal.getName()).thenReturn(userInfo.getUserId());

        Mockito.when(userInfoService.getById(mockPrincipal.getName())).thenReturn(userInfo);

        mockMvc.perform(get("/getInfo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }
    @Test
    void shouldGetUser() throws Exception {
        Mockito.when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/findOne"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}