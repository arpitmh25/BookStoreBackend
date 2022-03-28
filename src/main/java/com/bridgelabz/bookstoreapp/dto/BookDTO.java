package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//Created BookDTO class to get output in format of message with data
@Data
public class BookDTO {

    @NotEmpty(message = "BookName can not be null")
    private String bookName;

    @NotEmpty(message = "AuthorName can not be null")
    private String author;

    @NotEmpty(message = "BookImage can not be null")
    private String image;

    @NotNull(message = "Price can not be empty")
    private Integer price;

    @NotNull(message = "Quantity can not be empty")
    private Integer quantity;
}
