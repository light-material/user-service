package com.material.light.lmuserservice.service.data;

import com.material.light.lmuserservice.model.entity.User;
import com.material.light.lmuserservice.model.enums.ResponseEnum;
import com.material.light.lmuserservice.model.exception.DuplicateEntryException;
import com.material.light.lmuserservice.model.exception.GenericException;
import com.material.light.lmuserservice.model.exception.InvalidParameterException;
import com.material.light.lmuserservice.repository.UserRepository;
import com.material.light.lmuserservice.service.validator.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by djames
 * 04/10/2019  11:26 PM
 */
@Service
public class UserService {

    private ValidatorService validatorService;
    private UserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("addUser") ValidatorService validatorService, UserRepository userRepository) {
        this.validatorService = validatorService;
        this.userRepository = userRepository;
    }

    public User getUserByUserName(String userName) throws InvalidParameterException {
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new InvalidParameterException(ResponseEnum.INVALID_PARAMETER, "User record not found."));
    }

    public User getUserByEmailAddress(String emailAddress) throws InvalidParameterException {
        return userRepository.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new InvalidParameterException(ResponseEnum.INVALID_PARAMETER, "User record not found."));
    }

    public User addUser(User user) throws GenericException {
        validatorService.validate(user);
        if (userRepository.findByUsernameOrEmailAddress(user.getUsername(), user.getEmailAddress()).isPresent())
            throw new DuplicateEntryException(ResponseEnum.DUPLICATE_ENTRY);
        return userRepository.save(user);
    }

    public User updateUser(long id, User user) throws GenericException {
        validatorService.validate(user);
        User record = userRepository.findById(id)
                .orElseThrow(() -> new InvalidParameterException(ResponseEnum.INVALID_PARAMETER, "User record not found."));
        user.setId(record.getId());
        return userRepository.save(user);
    }
}
