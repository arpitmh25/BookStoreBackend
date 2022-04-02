package com.bridgelabz.bookstoreapp.controller;


import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Cart;
import com.bridgelabz.bookstoreapp.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
//Created controller so that we can perform rest api calls
@RestController
//Maps http request to method of mvc and rest controller
@RequestMapping("/book")
public class CartController {

    //Autowired ICartService interface so we can inject its dependency here
    @Autowired
    ICartService cartService;

    //Ability to store  items  record to repository
    @PostMapping("/addInCart")
    public ResponseEntity<ResponseDTO> insertItem(@Valid @RequestBody CartDTO cartdto) {
        Cart newCart = cartService.insertItems(cartdto);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    //Ability to get all record from repository
    @GetMapping("/allcartvalues")
    public ResponseEntity<ResponseDTO> getAllCartItems() {
        List<Cart> newCart = cartService.getAllCartItems();
        ResponseDTO responseDTO = new ResponseDTO("All records retrieved successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    //Ability to get cart  record by id
    @GetMapping("/get/{cartID}")
    public ResponseEntity<ResponseDTO> getCartRecord(@PathVariable Integer cartID) {
        Cart newCart = cartService.getCartRecordById(cartID);
        ResponseDTO responseDTO = new ResponseDTO("Record retrieved successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    //Ability to update cart items record to repository
    @PutMapping("/update/{cartID}")
    public ResponseEntity<ResponseDTO> updateCartItems(@PathVariable Integer cartID, @Valid @RequestBody CartDTO cartdto) {
        Cart newCart = cartService.updateCartItems(cartID, cartdto);
        ResponseDTO responseDTO = new ResponseDTO("Record updated successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    //Ability to delete  cart record to repository
    @DeleteMapping("/deletecart/{cartID}")
    public ResponseEntity<ResponseDTO> deleteCartItems(@PathVariable Integer cartID) {
        Cart newCart = cartService.deleteCartItems(cartID);
        ResponseDTO responseDTO = new ResponseDTO("Record deleted successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    //Ability to update quantity record to repository
    @PutMapping("/updateQuantity/{cartID}/{quantity}")
    public ResponseEntity<ResponseDTO> updateQuantityItems(@PathVariable Integer cartID, @PathVariable Integer quantity) {
        Cart newCart = cartService.updateQuantityItems(cartID, quantity);
        ResponseDTO responseDTO = new ResponseDTO("Quantity for book record updated successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<ResponseDTO> deleteBooks() {
        List<Cart> books = cartService.deleteAllFromCart();
        return new ResponseEntity(books, HttpStatus.OK);
    }

    @GetMapping("/decreaseQuantity/{cartID}")
    public ResponseEntity<ResponseDTO> decreaseQuantity(@PathVariable Integer cartID) {
        Cart newCart = cartService.decreaseQuantity(cartID);
        ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !", newCart);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/increaseQuantity/{cartID}")
    public ResponseEntity<ResponseDTO> increaseQuantity(@PathVariable Integer cartID) {
        Cart newCart = cartService.increaseQuantity(cartID);
        ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !", newCart);
        return new ResponseEntity(dto, HttpStatus.OK);
    }


}
