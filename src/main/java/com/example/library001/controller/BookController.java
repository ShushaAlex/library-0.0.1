package com.example.library001.controller;

import com.example.library001.entity.Book;
import com.example.library001.service.impl.BookService;
import jakarta.websocket.server.PathParam;
import lombok.extern.log4j.Log4j;
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
        log.info("hello");
        return bookService.findAll();
    }
//    @GetMapping("/{id}")
//    public Book getBook(@PathVariable Long id) {
//        log.info(String.valueOf(id));
//        return bookService.findByID(id);
//    }

    @GetMapping("/")
    public Book getBook(@RequestParam Long id) {
        log.info(String.valueOf(id));
        return bookService.findByID(id);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.save(book);
    }

}
