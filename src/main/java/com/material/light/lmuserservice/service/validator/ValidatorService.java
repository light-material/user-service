package com.material.light.lmuserservice.service.validator;

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

    void validate(Object o) throws GenericException;

    default void beanValidate(Object o) throws InvalidParameterException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(o);

        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<Object> violation = constraintViolations.iterator().next();
            String constraintViolationMessage = String.format("Invalid value[%s] for %s",
                    violation.getInvalidValue(), violation.getPropertyPath());
            throw new InvalidParameterException(ResponseEnum.INVALID_PARAMETER, constraintViolationMessage);
        }
    }
}
