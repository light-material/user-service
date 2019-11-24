package com.material.light.lmuserservice.repository;

import com.material.light.lmuserservice.model.entity.User;
import com.material.light.lmuserservice.model.enums.AccountStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by djames
 * 04/10/2019  11:21 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);

    Optional<User> findByEmailAddress(String emailAddress);

    Optional<User> findByUsernameOrEmailAddress(String username, String emailAddress);

    List<User> findByAccountStatusOrderByDateCreatedDesc(AccountStatus accountStatus, Pageable pageable);
}
