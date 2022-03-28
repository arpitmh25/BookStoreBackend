package com.bridgelabz.bookstoreapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

//Map to a database table
//Use to bundle features of getter and setter
//Created Order class with different fields
@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderID;

    private Integer price;
    private Integer quantity;
    private String address;
    private LocalDate currentDate = LocalDate.now();
    private boolean cancell;

    //It  fetches proxy of the child entities
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID_order")
    private UserRegistration userRegistration;

    //It  fetches proxy of the child entities
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookID_order")
    private Book book;

    public Orders(Integer price, Integer quantity, String address, Book book,
                  UserRegistration userRegistration, boolean cancell) {
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.book = book;
        this.userRegistration = userRegistration;
        this.cancell = cancell;

    }

    public Orders(Integer orderID, Integer price, Integer quantity, String address, Book book,
                  UserRegistration userRegistration, boolean cancell) {
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.book = book;
        this.userRegistration = userRegistration;
        this.cancell = cancell;

    }
}
