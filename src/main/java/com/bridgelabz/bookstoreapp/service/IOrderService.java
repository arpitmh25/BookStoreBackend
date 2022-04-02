package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.OrderDetails;
import com.bridgelabz.bookstoreapp.model.Orders;

import java.util.List;

//Created interface for all service methods to achieve abstraction
public interface IOrderService {
    public Orders insertOrderItem(OrderDetails orderDetails);

    public List<Orders> getAllOrderItems();

    public Orders getOrderItems(Integer orderID);

    public Orders updateOrderItems(Integer orderID, OrderDTO orderdto);

    public Orders deleteOrderItem(Integer orderID);
}
