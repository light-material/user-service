package com.material.light.lmuserservice.model.entity;

import com.google.gson.Gson;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by djames
 * 04/10/2019  11:17 PM
 */
@Data
@Entity
@Table(name = "login_credentials")
public class LoginCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}