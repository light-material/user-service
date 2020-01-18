package com.material.light.lmuserservice.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.material.light.lmuserservice.model.entity.LoginCredentials;
import com.material.light.lmuserservice.model.enums.ResponseEnum;
import com.material.light.lmuserservice.model.exception.DuplicateEntryException;
import com.material.light.lmuserservice.model.exception.GenericException;
import com.material.light.lmuserservice.model.exception.InvalidParameterException;
import com.material.light.lmuserservice.repository.LoginCredentialsRepository;
import com.material.light.lmuserservice.service.validator.ValidatorService;

@Service
public class LoginCredentialsService {

	private LoginCredentialsRepository loginCredentialsRepository;
	private ValidatorService validatorService;

	@Autowired
	public LoginCredentialsService(@Qualifier("addLoginCreds") ValidatorService validatorService,
			LoginCredentialsRepository loginCredentialsRepository) {
		this.validatorService = validatorService;
		this.loginCredentialsRepository = loginCredentialsRepository;
	}

	public LoginCredentials getLoginCredsByUserName(String userName) throws InvalidParameterException {
		return loginCredentialsRepository.findByUserName(userName).orElseThrow(
				() -> new InvalidParameterException(ResponseEnum.INVALID_PARAMETER, "Login Credentia record not found."));
	}

	public LoginCredentials addLoginCreds(LoginCredentials loginCreds) throws GenericException {
		validatorService.validate(loginCreds);
		if (loginCredentialsRepository.findByUserName(loginCreds.getUsername()).isPresent())
			throw new DuplicateEntryException(ResponseEnum.DUPLICATE_ENTRY);
		return loginCredentialsRepository.save(loginCreds);
	}

	public LoginCredentials updateLoginCreds(long id, LoginCredentials loginCreds) throws GenericException {
		validatorService.validate(loginCreds);
		LoginCredentials record = loginCredentialsRepository.findById(id)
				.orElseThrow(() -> new InvalidParameterException(ResponseEnum.INVALID_PARAMETER,
						"Login Credential record not found"));
		return loginCredentialsRepository.save(loginCreds);
	}
}
