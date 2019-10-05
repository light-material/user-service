package com.material.light.lmuserservice.controller;

import com.material.light.lmuserservice.model.contract.GenericResponse;
import com.material.light.lmuserservice.model.entity.User;
import com.material.light.lmuserservice.model.exception.InvalidParameterException;
import com.material.light.lmuserservice.service.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by djames
 * 01/10/2019  1:09 PM
 */
@RestController
@RequestMapping("/v1/user")
public class Controller {

    private UserService userService;

    @Autowired
    public Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userName}")
    public ResponseEntity<GenericResponse<User>> getUserByUserName(@PathVariable String userName)
            throws InvalidParameterException {
        User user = userService.getUserByUserName(userName);
        return ResponseEntity.ok(new GenericResponse<>(user));
    }
}
