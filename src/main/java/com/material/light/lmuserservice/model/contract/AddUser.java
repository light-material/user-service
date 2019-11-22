package com.material.light.lmuserservice.model.contract;

import com.google.gson.Gson;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by djames
 * 06/10/2019  10:30 PM
 */
public interface AddUser {

    @EqualsAndHashCode(callSuper = true)
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    class Request extends BaseRequest {
        @NotBlank
        @Size(max = 64)
        private String username;

        @NotBlank
        @Size(max = 64)
        private String firstName;

        @NotBlank
        @Size(max = 64)
        private String lastName;

        @NotBlank
        @Pattern(regexp = "^09\\d{9}")
        private String mobileNumber;

        @NotBlank
        @Size(max = 64)
        @Email
        private String emailAddress;

        @Override
        public String toString() {
            return new Gson().toJson(this);
        }
    }
}
