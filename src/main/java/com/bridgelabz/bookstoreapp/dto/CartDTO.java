package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

//Created CartDTO class to get output in format of message with data
@Data
public class CartDTO {
    private Integer userID;
    private Integer bookID;

    @NotNull(message = "Quantity can not be empty")
    private Integer quantity;
}
