package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.model.Book;

import java.util.List;

//Created interface for all service methods to achieve abstraction
public interface IBookStoreService {


    public Book saveDataToRepo(BookDTO bookDTO);

    public Book getRecordById(Integer bookID);

    public Book updateRecordById(Integer bookID, BookDTO bookDTO);

    public Book deleteRecordById(Integer bookID);

    List<Book> sortByPriceAscending();

    List<Book> sortByPriceDescending();

    public Book updateQuantity(Integer bookID, Integer quantity);

    List<Book> getAllData();

    List<Book> getSortedBook(String searchQuery, String sortOrder);

}
