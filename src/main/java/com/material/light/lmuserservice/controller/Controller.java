package com.material.light.lmuserservice.controller;

import com.material.light.lmuserservice.model.contract.GenericResponse;
import com.material.light.lmuserservice.model.entity.LoginCredentials;
import com.material.light.lmuserservice.model.entity.User;
import com.material.light.lmuserservice.model.exception.GenericException;
import com.material.light.lmuserservice.model.exception.InvalidParameterException;
import com.material.light.lmuserservice.service.data.LoginCredentialsService;
import com.material.light.lmuserservice.service.data.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by djames
 * 01/10/2019  1:09 PM
 */
@Slf4j
@RestController
@RequestMapping("/v1/user")
public class Controller {

    private UserService userService;
    private LoginCredentialsService loginCredentialsService;

    @Autowired
    public Controller(UserService userService, LoginCredentialsService loginCredentialsService) {
        this.userService = userService;
        this.loginCredentialsService = loginCredentialsService;
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<User>>> getActiveUsers(@RequestParam(value = "limit", required = false) Integer limit) {
        int max = limit != null ? limit : 10;
        log.info("GetActiveUsers : {}", max);
        List<User> users = userService.getActiveUsers(max);
        ResponseEntity<GenericResponse<List<User>>> response = ResponseEntity.ok(new GenericResponse<>(users));
        log.info("GetActiveUsers: {}", response);
        return response;

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
    
    public ResponseEntity<GenericResponse<LoginCredentials>> getLoginCredsByUserName(@RequestParam String userName)
    		throws InvalidParameterException{
		
    	log.info("GetLoginCredsByUserName {}", userName);
    	LoginCredentials loginCreds = loginCredentialsService.getLoginCredsByUserName(userName);
    	ResponseEntity<GenericResponse<LoginCredentials>> response = ResponseEntity.ok(new GenericResponse<>(loginCreds));
    	log.info("GetLoginCredsByUserName Response {}", response);
    	return response;
    }

    @PostMapping
    public ResponseEntity<GenericResponse<User>> addUser(@RequestBody User request) throws GenericException {
        log.info("AddUser Request: {}", request);
        User user = userService.addUser(request);
        ResponseEntity<GenericResponse<User>> response = ResponseEntity.ok(new GenericResponse<>(user));
        log.info("AddUser Response: {}", response);
        return response;
    }

    @PutMapping(value = "/{id}")
	public ResponseEntity<GenericResponse<User>> updateUser(@PathVariable long id,
                                                            @RequestBody User request) throws GenericException {
        log.info("UpdateUser[{}] Request: {}", id, request);
        User user = userService.updateUser(id, request);
        ResponseEntity<GenericResponse<User>> response = ResponseEntity.ok(new GenericResponse<>(user));
        log.info("UpdateUser Response: {}", response);
        return response;
    }
    
    @PostMapping
    public ResponseEntity<GenericResponse<LoginCredentials>> addLoginCreds(@RequestBody LoginCredentials request) throws GenericException{
    	log.info("AddLoginCreds Request: {}", request);
    	LoginCredentials loginCreds = loginCredentialsService.addLoginCreds(request);
    	ResponseEntity<GenericResponse<LoginCredentials>> response = ResponseEntity.ok(new GenericResponse<>(loginCreds));
    	log.info("AddLoginCreds Response: {}", response);
    	return response;
    }
    
	@PutMapping(value = "/{id}")
	public ResponseEntity<GenericResponse<LoginCredentials>> updateLoginCreds(@PathVariable long id,
			@RequestBody LoginCredentials request) throws GenericException {
		log.info("UpdateLoginCreds[{}] Request: {}", id, request);
		LoginCredentials loginCreds = loginCredentialsService.updateLoginCreds(id, request);
		ResponseEntity<GenericResponse<LoginCredentials>> response = ResponseEntity.ok(new GenericResponse<>(loginCreds));
		log.info("UpdateLoginCreds Response: {}", response);
		return response;
	}
}
