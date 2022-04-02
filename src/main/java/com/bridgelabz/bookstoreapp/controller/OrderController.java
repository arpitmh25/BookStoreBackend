package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.OrderDetails;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Cart;
import com.bridgelabz.bookstoreapp.model.Orders;
import com.bridgelabz.bookstoreapp.service.ICartService;
import com.bridgelabz.bookstoreapp.service.IOrderService;
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
public class OrderController {

    //Autowired IOrderService interface so we can inject its dependency here
    @Autowired
    IOrderService orderService;


    //Ability to store  orders  record to repository
    @PostMapping("/createorder")
    public ResponseEntity<ResponseDTO> insertOrderItem(@Valid @RequestBody OrderDetails orderDetails) {
        Orders newOrders = orderService.insertOrderItem(orderDetails);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully !", newOrders);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    //Ability to get all record from repository
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllOrderItems() {
        List<Orders> newOrders = orderService.getAllOrderItems();
        ResponseDTO responseDTO = new ResponseDTO("All records retrieved successfully !", newOrders);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    //Ability to get orders  record by id
    @GetMapping("/get/{orderID}")
    public ResponseEntity<ResponseDTO> getOrderItems(@PathVariable Integer orderID) {
        Orders newOrders = orderService.getOrderItems(orderID);
        ResponseDTO responseDTO = new ResponseDTO("Record retrieved successfully !", newOrders);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }


    //Ability to update order items record to repository
    @PutMapping("/update/{orderID}")
    public ResponseEntity<ResponseDTO> updateOrderItems(@PathVariable Integer orderID, @Valid @RequestBody OrderDTO orderdto) {
        Orders newOrders = orderService.updateOrderItems(orderID, orderdto);
        ResponseDTO responseDTO = new ResponseDTO("Record updated successfully !", newOrders);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    //Ability to delete  orders record to repository
    @DeleteMapping("/delete/{orderID}")
    public ResponseEntity<ResponseDTO> deleteOrderItem(@PathVariable Integer orderID) {
        Orders newOrders = orderService.deleteOrderItem(orderID);
        ResponseDTO responseDTO = new ResponseDTO("Record deleted successfully !", newOrders);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }
}
