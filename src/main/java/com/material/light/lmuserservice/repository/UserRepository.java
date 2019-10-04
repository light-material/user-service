package com.material.light.lmuserservice.repository;

import com.material.light.lmuserservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by djames
 * 04/10/2019  11:21 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
