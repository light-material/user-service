package com.material.light.lmuserservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.material.light.lmuserservice.model.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by djames
 * 04/10/2019  11:12 PM
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String emailAddress;
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @JsonIgnore
    @Version
    private int version;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
