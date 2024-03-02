package com.example.library001.service.impl;

import com.example.library001.entity.Book;
import com.example.library001.entity.Student;
import com.example.library001.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    //@Autowired
    private BookRepository bookRepository;
    //@Autowired
    private StudentRepository studentRepository;
    //@Autowired
    private StudentBookRepository studentBookRepository;
    @Override
    public Book saveBook(Book book) {
       return bookRepository.save(book);
    }

    @Override
    public void removeBook(Book book) {

    }

    @Override
    public Book borrowBook(int bookId, int studentId) {

        Book book = findBookById(bookId);
        Student student = studentRepository.findStudentById(studentId);

        if(book != null && book.isAvailable() && student != null) {
            book.setAvailable(false);
            studentBookRepository.save(bookId, studentId, true);

            return saveBook(book);
        }

        return null;
    }

    @Override
    public Book returnBook(int bookId) {

        Book book = findBookById(bookId);
        Student student = studentRepository.findStudentById(studentId);

        if(book != null && !book.isAvailable() && student != null) {
            book.setAvailable(true);
            studentBookRepository.update(bookId);

            return saveBook(book);
        }

        return null;
    }

    @Override
    public List<Book> getBooksList() {
        return bookRepository.getBooksList();
    }

    @Override
    public Book findBookById(int bookId) {
        return bookRepository.findBookById(bookId);
    }
}
