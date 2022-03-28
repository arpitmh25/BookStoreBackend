package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

//Created OrderRepository extending JpaRepository so that we perform CURD and can implement custom query methods
public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
