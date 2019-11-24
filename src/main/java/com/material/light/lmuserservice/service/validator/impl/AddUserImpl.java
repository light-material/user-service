package com.material.light.lmuserservice.service.validator.impl;

import com.material.light.lmuserservice.model.entity.User;
import com.material.light.lmuserservice.model.exception.GenericException;
import com.material.light.lmuserservice.service.validator.ValidatorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by djames
 * 06/10/2019  10:48 PM
 */
@Service
@Qualifier("addUser")
public class AddUserImpl implements ValidatorService {

    @Override
    public void validate(Object o) throws GenericException {
        User request = (User) o;
        beanValidate(request);
    }
}
