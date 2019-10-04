package com.material.light.lmuserservice.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by djames
 * 04/10/2019  11:12 PM
 */
@Data
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
    private String accountStatus;
    @Version
    private int version;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", dateCreated=" + dateCreated +
                ", accountStatus='" + accountStatus + '\'' +
                ", version=" + version +
                '}';
    }
}
