package com.material.light.lmuserservice.controller;

import com.material.light.lmuserservice.model.contract.GenericResponse;
import com.material.light.lmuserservice.model.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * Created by djames
 * 06/10/2019  3:43 PM
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getUserByUserName() {
        log.info("Testing GetUserByUserName...");
        ResponseEntity<GenericResponse> response = testRestTemplate.getForEntity("/v1/user?username=gon_frix", GenericResponse.class);
        log.info("GetUserByUsername Test Response: {}", response);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getResultCode()).isEqualTo(ResponseEnum.SUCCESS.getResultCode());
        Assertions.assertThat(response.getBody().getResultMessage()).isEqualTo(ResponseEnum.SUCCESS.getResultMessage());
        Assertions.assertThat(response.getBody().getResultNamespace()).isEqualTo(ResponseEnum.SUCCESS.getResultNamespace());
        Assertions.assertThat(response.getBody().getResultValue()).isNotNull();

        Map<String, String> user = (Map<String, String>) response.getBody().getResultValue();
        Assertions.assertThat(user.get("username")).isEqualToIgnoringCase("gon_frix");
        Assertions.assertThat(user.get("firstName")).isEqualToIgnoringCase("Gon");
        Assertions.assertThat(user.get("lastName")).isEqualToIgnoringCase("Frix");
        Assertions.assertThat(user.get("accountStatus")).isEqualToIgnoringCase("ACTIVE");
    }

    @Test
    void getUserByEmailAddress() {
        log.info("Testing GetUserByEmailAddress...");
        ResponseEntity<GenericResponse> response = testRestTemplate.getForEntity("/v1/user?email=killua.zoldyck@gmail.com", GenericResponse.class);
        log.info("GetUserByEmailAddress Test Response: {}", response);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getResultCode()).isEqualTo(ResponseEnum.SUCCESS.getResultCode());
        Assertions.assertThat(response.getBody().getResultMessage()).isEqualTo(ResponseEnum.SUCCESS.getResultMessage());
        Assertions.assertThat(response.getBody().getResultNamespace()).isEqualTo(ResponseEnum.SUCCESS.getResultNamespace());
        Assertions.assertThat(response.getBody().getResultValue()).isNotNull();

        Map<String, String> user = (Map<String, String>) response.getBody().getResultValue();
        Assertions.assertThat(user.get("emailAddress")).isEqualToIgnoringCase("killua.zoldyck@gmail.com");
        Assertions.assertThat(user.get("username")).isEqualToIgnoringCase("kill_zold");
        Assertions.assertThat(user.get("firstName")).isEqualToIgnoringCase("Killua");
        Assertions.assertThat(user.get("lastName")).isEqualToIgnoringCase("Zoldyck");
        Assertions.assertThat(user.get("accountStatus")).isEqualToIgnoringCase("ACTIVE");
    }
}