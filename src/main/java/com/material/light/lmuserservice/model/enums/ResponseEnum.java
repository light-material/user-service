package com.material.light.lmuserservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by djames
 * 05/10/2019  7:04 PM
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(200, "SUCCES", "Success.", "user-service"),
    INVALID_PARAMETER(400, "INVALID_PARAMETER", "Invalid request parameters.", "user-service"),
    DUPLICATE_ENTRY(400, "DUPLICATE_ENTRY", "Username / Email Address is already existing.", "user-service"),
    UNKNOWN_EXCEPTION(500, "UNKNOWN_EXCEPTION", "API failed due to unknown reason.", "user-service");

    private int statusCode;
    private String resultCode;
    private String resultMessage;
    private String resultNamespace;

}
