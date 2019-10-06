package com.material.light.lmuserservice.service.validator;

import com.material.light.lmuserservice.model.contract.BaseRequest;
import com.material.light.lmuserservice.model.enums.ResponseEnum;
import com.material.light.lmuserservice.model.exception.GenericException;
import com.material.light.lmuserservice.model.exception.InvalidParameterException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by djames
 * 06/10/2019  10:43 PM
 */
public interface ValidatorService {

    void validate(BaseRequest baseRequest) throws GenericException;

    default void beanValidate(BaseRequest baseRequest) throws InvalidParameterException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<BaseRequest>> constraintViolations = validator.validate(baseRequest);

        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<BaseRequest> violation = constraintViolations.iterator().next();
            String constraintViolationMessage = String.format("Invalid value[%s] for %s",
                    violation.getInvalidValue(), violation.getPropertyPath());
            throw new InvalidParameterException(ResponseEnum.INVALID_PARAMETER, constraintViolationMessage);
        }
    }
}
