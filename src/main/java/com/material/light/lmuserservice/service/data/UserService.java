package com.material.light.lmuserservice.service.data;

import com.material.light.lmuserservice.model.entity.User;
import com.material.light.lmuserservice.model.enums.ResponseEnum;
import com.material.light.lmuserservice.model.exception.InvalidParameterException;
import com.material.light.lmuserservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by djames
 * 04/10/2019  11:26 PM
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUserName(String userName) throws InvalidParameterException {
        return userRepository.getUserByUsername(userName)
                .orElseThrow(() -> new InvalidParameterException(ResponseEnum.INVALID_PARAMETER, "User record not found."));
    }
}
