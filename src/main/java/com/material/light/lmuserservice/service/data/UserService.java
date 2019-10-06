package com.material.light.lmuserservice.service.data;

import com.material.light.lmuserservice.model.contract.AddUser;
import com.material.light.lmuserservice.model.entity.User;
import com.material.light.lmuserservice.model.enums.AccountStatus;
import com.material.light.lmuserservice.model.enums.ResponseEnum;
import com.material.light.lmuserservice.model.exception.DuplicateEntryException;
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

    public User getUserByEmailAddress(String emailAddress) throws InvalidParameterException {
        return userRepository.getUserByEmailAddress(emailAddress)
                .orElseThrow(() -> new InvalidParameterException(ResponseEnum.INVALID_PARAMETER, "User record not found."));
    }

    public User addUser(AddUser.Request request) throws DuplicateEntryException {
        if (userRepository.getUserByUsernameOrEmailAddress(request.getUsername(), request.getEmailAddress()).isPresent())
            throw new DuplicateEntryException(ResponseEnum.DUPLICATE_ENTRY);

        User user = User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .mobileNumber(request.getMobileNumber())
                .emailAddress(request.getEmailAddress())
                .accountStatus(AccountStatus.ACTIVE)
                .build();
        return userRepository.save(user);
    }
}
