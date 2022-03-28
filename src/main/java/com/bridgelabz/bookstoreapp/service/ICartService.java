package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.model.Cart;

import java.util.List;

//Created interface for all service methods to achieve abstraction
public interface ICartService {
    public Cart insertItems(CartDTO cartdto);

    public List<Cart> getAllCartItems();

    public Cart getCartRecordById(Integer cartID);

    public Cart updateCartItems(Integer cartID, CartDTO cartdto);

    public Cart deleteCartItems(Integer cartID);

    public Cart updateQuantityItems(Integer cartID, Integer quantity);

    public List<Cart> deleteAllFromCart();

    public Cart decreaseQuantity(Integer cartID);

    public Cart increaseQuantity(Integer cartID);
}
