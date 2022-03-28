package com.bridgelabz.bookstoreapp.controller;


import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.service.IBookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@CrossOrigin
//Created controller so that we can perform rest api calls
@RestController
//Maps http request to method of mvc and rest controller
@RequestMapping("/book")

public class BookController {
    //Autowired IBookStoreService interface so we can inject its dependency here
    @Autowired
    IBookStoreService service;


    //Ability to store  books  record to repository
    @PostMapping("/add")
    public ResponseEntity<String> saveDataToRepo(@Valid @RequestBody BookDTO bookDTO) {
        Book newBook = service.saveDataToRepo(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Book Record created successfully", newBook);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    //Ability to get all record from repository
    @GetMapping("/getbook")
    public ResponseEntity<ResponseDTO> getBooks(@RequestParam String searchQuery , @RequestParam String sortOrder) {
        List<Book> bookData = Collections.emptyList();
        if(searchQuery == null || searchQuery.isEmpty()) {
            bookData = service.getAllData();
        }else {
            bookData = service.getBooksByName(searchQuery);
        }

        if (sortOrder!=null && !sortOrder.isEmpty() ) {
            if(sortOrder.toLowerCase().equals("asc")) {
                Collections.sort(bookData, (o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
            }
            else if (sortOrder.toLowerCase().equals("desc")){
                Collections.sort(bookData, (o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
            }
        }

        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Success", bookData), HttpStatus.OK);
    }

    //Ability to get bookstoreapp  record by id
    @GetMapping("/get/{bookID}")
    public ResponseEntity<String> getRecordFromRepoByID(@PathVariable Integer bookID) {
        Book book = service.getRecordById(bookID);
        ResponseDTO responseDTO = new ResponseDTO(" Book Record for particular id retrieved successfully", book);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    //Ability to update bookstoreapp record to repository
    @PutMapping("/update/{bookID}")
    public ResponseEntity<String> updateRecordById(@PathVariable Integer bookID, @RequestBody BookDTO bookDTO) {
        Book newBook = service.updateRecordById(bookID, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO(" Book Record updated successfully", newBook);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    //Ability to delete  bookstore record to repository
    @DeleteMapping("/deletebook/{bookID}")
    public ResponseEntity<String> deleteRecordById(@PathVariable Integer bookID) {
        ResponseDTO dto = new ResponseDTO(" Book Record deleted successfully", service.deleteRecordById(bookID));
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    //Ability to search  book record by name
    @GetMapping("/bookname/{bookName}")
    public ResponseEntity getBookByName(@PathVariable String bookName) {
        Book newBook = service.getBookByName(bookName);
        ResponseDTO responseDTO = new ResponseDTO("Record for particular book retrieved successfully !", newBook);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    //Ability to sort  book record by increasing order
    @GetMapping("/sortascending")
    public List<Book> sortAscending() {
        return service.sortByPriceAscending();
    }

    //Ability to sort  book record by decreasing order
    @GetMapping("/sortdescending")
    public List<Book> sortDescending() {
        return service.sortByPriceDescending();
    }

    //Ability to update quantity  book record by ids
    @PutMapping("/updatingquantity/{bookID}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable Integer bookID, @RequestParam Integer quantity) {
        Book newBook = service.updateQuantity(bookID, quantity);
        ResponseDTO responseDTO = new ResponseDTO(" Book Record quantity updated successfully", newBook);
        return new ResponseEntity(responseDTO, HttpStatus.OK);

    }
}
