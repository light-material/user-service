package com.material.light.lmuserservice.model.exception;

import com.material.light.lmuserservice.model.enums.ResponseEnum;

/**
 * Created by djames
 * 05/10/2019  7:52 PM
 */
public class InvalidParameterException extends GenericException {
    public InvalidParameterException(ResponseEnum responseEnum, String resultMessage) {
        super(responseEnum, resultMessage);
    }
}
