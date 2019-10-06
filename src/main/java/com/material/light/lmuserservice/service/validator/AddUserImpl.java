package com.material.light.lmuserservice.service.validator;

import com.material.light.lmuserservice.model.contract.AddUser;
import com.material.light.lmuserservice.model.contract.BaseRequest;
import com.material.light.lmuserservice.model.exception.GenericException;
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
    public void validate(BaseRequest baseRequest) throws GenericException {
        AddUser.Request request = (AddUser.Request) baseRequest;
        beanValidate(request);
    }
}
