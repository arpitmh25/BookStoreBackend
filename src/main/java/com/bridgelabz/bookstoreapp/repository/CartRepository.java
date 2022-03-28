package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

//Created CartRepository extending JpaRepository so that we perform CURD and can implement custom query methods
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
