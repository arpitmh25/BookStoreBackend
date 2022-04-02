package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.Book;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookStoreService implements IBookStoreService {


    //Autowired repository class to inject its dependency
    @Autowired
    private BookRepository bookRepository;

    //Created service method which serves controller api to post data
    public Book saveDataToRepo(BookDTO bookDTO) {
        Book newBook = new Book(bookDTO);
        bookRepository.save(newBook);
        return newBook;

    }

    //Created service method which serves controller api to retrieve all records
    @Override
    public List<Book> getAllData() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    private List<Book> getBooksByName(String searchQuery) {
        return bookRepository.getBooksByName(searchQuery);

    }

    //Created service method which serves controller api to get record by id
    @Override
    public Book getRecordById(Integer bookID) {
        Optional<Book> book = bookRepository.findById(bookID);
        if (book.isEmpty()) {
            throw new BookStoreException("Book record not exist");
        } else {
            return book.get();
        }
    }


    //Created service method which serves controller api to update record by id
    @Override
    public Book updateRecordById(Integer bookID, BookDTO bookDTO) {
        Optional<Book> book = bookRepository.findById(bookID);
        if (book.isEmpty()) {
            throw new BookStoreException("Book record not exist");
        } else {
            Book newBook = new Book(bookID, bookDTO);
            bookRepository.save(newBook);
            return newBook;
        }
    }

    //Created service method which serves controller api to delete record by id
    @Override
    public Book deleteRecordById(Integer bookID) {
        Optional<Book> newBook = bookRepository.findById(bookID);
        if (newBook.isPresent()) {
            throw new BookStoreException("Book record not exist");
        } else {
            bookRepository.deleteById(bookID);
            return newBook.get();
        }
    }

    //Created service method which serves controller api to get book record by name
//    @Override
//    public Book getBookByName(String bookName) {
//        Optional<Book> book = bookRepository.getBookByName(bookName);
//        if (book.isEmpty()) {
//            throw new BookStoreException("Book doesn't exists");
//        } else {
//            return book.get();
//        }
//    }

    //Created service method which serves controller api to sort in ascending order
    @Override
    public List<Book> sortByPriceAscending() {
        List<Book> getBookList = bookRepository.getSortedReverseListOfBooks();
        return getBookList;
    }

    //Created service method which serves controller api to sort in descending order
    @Override
    public List<Book> sortByPriceDescending() {
        List<Book> getBookList = bookRepository.getSortedListOfBooks();
        return getBookList;
    }

    //Created service method which serves controller api to update quantity
    @Override
    public Book updateQuantity(Integer bookID, Integer quantity) {
        Optional<Book> newBook = bookRepository.findById(bookID);
        if (newBook.isEmpty()) {
            throw new BookStoreException("Book doesn't exists");
        } else {
            newBook.get().setQuantity(quantity);
            return newBook.get();

        }
    }

    //Created service method which serves controller to sort and search
    public List<Book> getSortedBook(String searchQuery, String sortOrder) {
        List<Book> bookData;
        if (searchQuery == null || searchQuery.isEmpty()) {
            bookData = getAllData();
        } else {
            bookData = getBooksByName(searchQuery);
        }

        if (sortOrder != null && !sortOrder.isEmpty()) {
            if (sortOrder.equalsIgnoreCase("asc")) {
                bookData.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
            } else if (sortOrder.equalsIgnoreCase("desc")) {
                bookData.sort((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
            }
        }
        return bookData;
    }
}
