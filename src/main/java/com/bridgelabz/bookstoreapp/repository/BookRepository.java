package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

//Created BookRepository extending JpaRepository so that we perform CURD and can implement custom query methods
public interface BookRepository extends JpaRepository<Book, Integer> {
//    @Query(value = "SELECT * FROM book where book_name=:bookName", nativeQuery = true)
//    Optional<Book> getBookByName(String bookName);

    @Query(value = "SELECT * FROM book order by book_name", nativeQuery = true)
    List<Book> getSortedListOfBooks();

    @Query(value = "SELECT * FROM book order by book_name desc", nativeQuery = true)
    List<Book> getSortedReverseListOfBooks();

    @Query(value = "SELECT * FROM book where book_name like :bookName%", nativeQuery = true)
    List<Book> getBooksByName(String bookName);
}
