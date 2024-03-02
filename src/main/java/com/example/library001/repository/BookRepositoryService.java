package com.example.library001.repository;

import com.example.library001.entity.Book;

import java.util.List;

public interface BookRepositoryService {
    Book save(Book book);
    List<Book> getBooksList();
    Book findBookById(int id);
}
