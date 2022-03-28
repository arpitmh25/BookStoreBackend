package com.bridgelabz.bookstoreapp.model;

import lombok.Data;

import javax.persistence.*;

//Map to a database table
@Entity
//Use to bundle features of getter and setter
@Data
//Created Cart class with different fields
@Table(name = "cart_details")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartID;
    private Integer quantity;

    //It  fetches proxy of the child entities
    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private UserRegistration userRegistration;

    //It  fetches proxy of the child entities
    @ManyToOne
    @JoinColumn(name = "bookID", referencedColumnName = "bookID")
    private Book book;

    public Cart(Integer quantity, Book book, UserRegistration userRegistration) {
        super();
        this.quantity = quantity;
        this.book = book;
        this.userRegistration = userRegistration;
    }

    public Cart() {
        super();
    }

    public Cart(Integer cartID, Integer quantity, Book book, UserRegistration userRegistration) {
        super();
        this.cartID = cartID;
        this.quantity = quantity;
        this.book = book;
        this.userRegistration = userRegistration;
    }
}
