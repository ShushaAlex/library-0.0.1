package com.example.library001.service;

import com.example.library001.entity.Book;

import java.util.List;

public interface BookService {
    Book saveBook(Book book);
    void removeBook(Book book);
    Book borrowBook(int bookId, int studentId);
    Book returnBook(int bookId, int studentId);
    List<Book> getBooksList();
    Book findBookById(int bookId);
}
