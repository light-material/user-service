package com.material.light.lmuserservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.material.light.lmuserservice.model.entity.User;
import com.material.light.lmuserservice.model.enums.AccountStatus;
import com.material.light.lmuserservice.service.data.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by djames
 * 06/10/2019  3:43 PM
 */
@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
@WebAppConfiguration
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void getUserByUserName() throws Exception {
        log.info("Testing GetUserByUserName...");

        User user = User.builder()
                .username("gon_frix")
                .firstName("Gon")
                .lastName("Frix")
                .mobileNumber("09279124037")
                .emailAddress("gon_frix@gmail.com")
                .accountStatus(AccountStatus.ACTIVE)
                .build();

        BDDMockito.given(userService.getUserByUserName(ArgumentMatchers.anyString())).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/user?username=gon_frix"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.username").value("gon_frix"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.firstName").value("Gon"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.lastName").value("Frix"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.mobileNumber").value("09279124037"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.emailAddress").value("gon_frix@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.accountStatus").value("ACTIVE"));
    }

    @Test
    public void getUserByEmailAddress() throws Exception {
        log.info("Testing getUserByEmailAddress...");

        User user = User.builder()
                .username("gon_frix")
                .firstName("Gon")
                .lastName("Frix")
                .mobileNumber("09279124037")
                .emailAddress("gon_frix@gmail.com")
                .accountStatus(AccountStatus.ACTIVE)
                .build();

        BDDMockito.given(userService.getUserByEmailAddress(ArgumentMatchers.anyString())).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/user?email=gon_frix@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.username").value("gon_frix"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.firstName").value("Gon"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.lastName").value("Frix"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.mobileNumber").value("09279124037"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.emailAddress").value("gon_frix@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.accountStatus").value("ACTIVE"));
    }

    @Test
    public void addUser() throws Exception {
        log.info("Testing addUser...");

        User user = User.builder()
                .username("user001")
                .firstName("DJames")
                .lastName("Castillo")
                .mobileNumber("09275525454")
                .emailAddress("djames@gmail.com")
                .accountStatus(AccountStatus.ACTIVE)
                .build();
        BDDMockito.given(userService.addUser(ArgumentMatchers.any())).willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(user)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("resultCode").value("SUCCESS"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultMessage").value("Success."))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.username").value("user001"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.firstName").value("DJames"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.lastName").value("Castillo"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.mobileNumber").value("09275525454"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.emailAddress").value("djames@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("resultValue.accountStatus").value("ACTIVE"));
    }
}