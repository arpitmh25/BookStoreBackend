package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderDetails {

    private Integer userID;
    @NotEmpty(message = "Address can not be empty")
    private String address;
}
