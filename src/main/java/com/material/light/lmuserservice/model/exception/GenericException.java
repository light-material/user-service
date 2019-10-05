package com.material.light.lmuserservice.model.exception;

import com.material.light.lmuserservice.model.enums.ResponseEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by djames
 * 05/10/2019  7:46 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GenericException extends Exception {
    private int statusCode;
    private String resultCode;
    private String resultMessage;
    private String resultNamespace;

    GenericException(ResponseEnum responseEnum) {
        super(responseEnum.getResultMessage());
        this.statusCode = responseEnum.getStatusCode();
        this.resultCode = responseEnum.getResultCode();
        this.resultMessage = responseEnum.getResultMessage();
        this.resultNamespace = responseEnum.getResultNamespace();
    }

    GenericException(ResponseEnum responseEnum, String resultMessage) {
        super(responseEnum.getResultMessage());
        this.statusCode = responseEnum.getStatusCode();
        this.resultCode = responseEnum.getResultCode();
        this.resultMessage = resultMessage;
        this.resultNamespace = responseEnum.getResultNamespace();
    }
}
