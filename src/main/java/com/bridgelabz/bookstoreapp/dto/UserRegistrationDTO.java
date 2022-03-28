package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

//Created UserRegistrationDTO class to get output in format of message with data
@Data
public class UserRegistrationDTO {

    @NotEmpty(message = "First name cant be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$", message = "Employee firstName is Invalid")
    private String firstName;

    @NotEmpty(message = "Last name cant be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$", message = "Employee lastname is Invalid")
    private String lastName;

    @Email
    private String email;

    @NotEmpty(message = "Address can not be empty")
    private String address;

    @NotEmpty(message = "Password can not be null")
    private String password;
}
