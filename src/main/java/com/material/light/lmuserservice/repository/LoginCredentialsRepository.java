package com.material.light.lmuserservice.repository;

import com.material.light.lmuserservice.model.entity.LoginCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by djames
 * 04/10/2019  11:22 PM
 */
@Repository
public interface LoginCredentialsRepository extends JpaRepository<LoginCredentials, Long> {
}
