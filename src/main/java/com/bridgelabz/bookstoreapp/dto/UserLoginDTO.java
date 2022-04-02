package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

//Created UserLoginDTO class to get output in format of message with data
@Data
public class UserLoginDTO {
    @Email
    private String email;
    @NotEmpty(message = "Password cant be null")
    private String password;

    public UserLoginDTO() {
        this.email=getEmail();
        this.password=getPassword();
    }
}
