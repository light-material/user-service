package com.material.light.lmuserservice.model.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.material.light.lmuserservice.model.enums.ResponseEnum;
import com.material.light.lmuserservice.model.exception.GenericException;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by djames
 * 05/10/2019  7:02 PM
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {
    private String resultCode;
    private String resultMessage;
    private String resultNamespace;
    private T resultValue;

    public GenericResponse(GenericException exception) {
        this.resultCode = exception.getResultCode();
        this.resultMessage = exception.getResultMessage();
        this.resultNamespace = exception.getResultNamespace();
    }

    public GenericResponse(ResponseEnum responseEnum) {
        this.resultCode = responseEnum.getResultCode();
        this.resultMessage = responseEnum.getResultMessage();
        this.resultNamespace = responseEnum.getResultNamespace();
    }

    public GenericResponse(T resultValue) {
        this.resultCode = ResponseEnum.SUCCESS.getResultCode();
        this.resultMessage = ResponseEnum.SUCCESS.getResultMessage();
        this.resultNamespace = ResponseEnum.SUCCESS.getResultNamespace();
        this.resultValue = resultValue;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
