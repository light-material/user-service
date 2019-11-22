package com.material.light.lmuserservice.controller;

import com.material.light.lmuserservice.model.contract.GenericResponse;
import com.material.light.lmuserservice.model.entity.User;
import com.material.light.lmuserservice.model.exception.GenericException;
import com.material.light.lmuserservice.model.exception.InvalidParameterException;
import com.material.light.lmuserservice.service.data.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by djames
 * 01/10/2019  1:09 PM
 */
@Slf4j
@RestController
@RequestMapping("/v1/user")
public class Controller {

    private UserService userService;

    @Autowired
    public Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(params = "username")
    public ResponseEntity<GenericResponse<User>> getUserByUserName(@RequestParam String username)
            throws InvalidParameterException {
        log.info("GetUserByUsername: {}", username);
        User user = userService.getUserByUserName(username);
        ResponseEntity<GenericResponse<User>> response = ResponseEntity.ok(new GenericResponse<>(user));
        log.info("GetUserByUsername Response: {}", response);
        return response;
    }

    @GetMapping(params = "email")
    public ResponseEntity<GenericResponse<User>> getUserByEmailAddress(@RequestParam String email)
            throws InvalidParameterException {
        log.info("GetUserByEmailAddress: {}", email);
        User user = userService.getUserByEmailAddress(email);
        ResponseEntity<GenericResponse<User>> response = ResponseEntity.ok(new GenericResponse<>(user));
        log.info("GetUserByEmailAddress Response: {}", response);
        return response;
    }

    @PostMapping
    public ResponseEntity<GenericResponse<User>> addUser(@RequestBody User request) throws GenericException {
        log.info("Add User Request: {}", request);
        User user = userService.addUser(request);
        ResponseEntity<GenericResponse<User>> response = ResponseEntity.ok(new GenericResponse<>(user));
        log.info("AddUser Response: {}", response);
        return response;
    }
}
