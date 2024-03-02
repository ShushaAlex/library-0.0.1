package com.example.library001.repository.impl;

import com.example.library001.entity.Book;
import com.example.library001.repository.BookRepositoryService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepository implements BookRepositoryService {
    private List<Book> bookList = new ArrayList<>();
    @Override
    public Book save(Book book) {
        Book bookCheck = findBookById(book.getId());
        if (bookCheck == null) {
            bookList.add(book);
        }
        return book;
    }

    @Override
    public List<Book> getBooksList() {
        return List.copyOf(bookList);
    }

    @Override
    public Book findBookById(int id) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
