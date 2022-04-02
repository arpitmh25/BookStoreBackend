package com.bridgelabz.bookstoreapp.dto;

import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.model.UserRegistration;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//Created OrderDTO class to get output in format of message with data
@Data
public class OrderDTO {
    private Integer userID;
    private Integer bookID;

    @NotNull(message = "Quantity can not be empty")
    private Integer quantity;
    @NotEmpty(message = "Address can not be empty")
    private String address;
    @NotNull(message = "Price can not be empty")
    private Integer price;

    private boolean cancell;
}
