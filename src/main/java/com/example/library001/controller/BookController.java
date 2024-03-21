package com.example.library001.controller;

import com.example.library001.entity.Book;
import com.example.library001.entity.StudentBook;
import com.example.library001.service.impl.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/")
    public Book getBook(@RequestParam Long id) {
        log.info(String.valueOf(id));
        return bookService.findByID(id);
    }

    @GetMapping("/byTitle")
    public Book getBookByTitle(@RequestParam String title) {
        log.info(title);
        try {
            return bookService.findByTitle(title);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
