package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

//Created ResponseDTO class to get output in format of message with data
@Data
public class ResponseDTO {
    private String message;
    private Object data;

    public ResponseDTO(String message, Object data) {
        super();
        this.message = message;
        this.data = data;
    }
}