package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Map to a database table
@Entity
//Use to bundle features of getter and setter
@Data
@NoArgsConstructor
//Created Book class with different fields
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookID;

    private String bookName;
    private String author;
    private String image;
    private Integer price;
    private Integer quantity;

    public Book(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.author = bookDTO.getAuthor();
        this.price = bookDTO.getPrice();
        this.image = bookDTO.getImage();
        this.quantity = bookDTO.getQuantity();
    }

    public Book(Integer bookID, BookDTO bookDTO) {
        this.bookID = bookID;
        this.bookName = bookDTO.getBookName();
        this.author = bookDTO.getAuthor();
        this.price = bookDTO.getPrice();
        this.image = bookDTO.getImage();
        this.quantity = bookDTO.getQuantity();
    }
}
