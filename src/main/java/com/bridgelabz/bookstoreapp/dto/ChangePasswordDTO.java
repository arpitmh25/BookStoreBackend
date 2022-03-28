package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;


//Created ChangePasswordDTO class to get output in format of message with data
@Data
public class ChangePasswordDTO {
    private String email;
    private String newPassword;
    private String token;

}
