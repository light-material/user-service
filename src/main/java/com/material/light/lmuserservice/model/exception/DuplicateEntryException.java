package com.material.light.lmuserservice.model.exception;

import com.material.light.lmuserservice.model.enums.ResponseEnum;

/**
 * Created by djames
 * 06/10/2019  11:13 PM
 */
public class DuplicateEntryException extends GenericException {
    public DuplicateEntryException(ResponseEnum responseEnum) {
        super(responseEnum);
    }
}
