package com.material.light.lmuserservice.controller.advice;

import com.material.light.lmuserservice.model.contract.GenericResponse;
import com.material.light.lmuserservice.model.enums.ResponseEnum;
import com.material.light.lmuserservice.model.exception.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by djames
 * 06/10/2019  3:14 PM
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = GenericException.class)
    public ResponseEntity<GenericResponse> genericExceptionHandler(GenericException e) {
        log.error("GenericException encountered: message:{} | causedBy: {}", e.getMessage(), e.getCause());
        log.error("", e);

        ResponseEntity<GenericResponse> response = ResponseEntity.status(e.getStatusCode())
                .body(new GenericResponse(e));
        log.error("Response: {}", response);
        return response;
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<GenericResponse> generalExceptionHandler(Exception e) {
        log.error("Exception encountered: message: {} | causedBy: {}", e.getMessage(), e.getCause());
        log.error("", e);

        ResponseEntity<GenericResponse> response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GenericResponse(ResponseEnum.UNKNOWN_EXCEPTION));
        log.error("Response: {}", response);
        return response;
    }
}
