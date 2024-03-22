package com.example.library001.controller;

import com.example.library001.entity.Book;
import com.example.library001.entity.StudentBook;
import com.example.library001.exception.NoBookException;
import com.example.library001.service.impl.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    //TODO add exception message to http response
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/")
    public Book getBook(@RequestParam Long id) throws NoBookException {
        log.info(String.valueOf(id));
        return bookService.findByID(id);
    }

    @GetMapping("/byTitle")
    public Book getBookByTitle(@RequestParam String title) {
        log.info(title);
        try {
            return bookService.findByTitle(title);
        } catch (NoBookException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }

    @GetMapping("/getLog")
    public List<StudentBook> getAllStudentBooks() {

        return bookService.findAllStudentBook();
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PostMapping("/borrow")
    public StudentBook borrowBook(@RequestParam Long bookID, @RequestParam Long studentID) {
        try {
            return bookService.borrowBook(bookID, studentID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/borrow/byTitle")
    public StudentBook borrowBook(@RequestParam String bookTitle, @RequestParam Long studentID) {
        try {
            return bookService.borrowBook(bookTitle, studentID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/return")
    public StudentBook returnBook(@RequestParam Long bookID) {
        try {
            return bookService.returnBook(bookID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/return/byTitle")
    public StudentBook returnBook(@RequestParam String bookTitle) {
        try {
            return bookService.returnBook(bookTitle);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
