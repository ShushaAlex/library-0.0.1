package com.example.library001.service.impl;

import com.example.library001.entity.Book;
import com.example.library001.entity.Student;
import com.example.library001.repository.impl.BookRepository;
import com.example.library001.repository.impl.StudentBookRepository;
import com.example.library001.repository.impl.StudentRepository;
import com.example.library001.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository = new BookRepository();
    @Autowired
    private StudentRepository studentRepository = StudentRepository.getInstance();
    @Autowired
    private StudentBookRepository studentBookRepository = new StudentBookRepository();

    @Override
    public Book saveBook(Book book) {

       return bookRepository.save(book);
    }
    @Override
    public Book borrowBook(int bookId, int studentId) {

        Book book = findBookById(bookId);
        Student student = studentRepository.findStudentById(studentId);

        if (book != null && book.isAvailable() && student != null) {
            book.setAvailable(false);
            studentBookRepository.save(bookId, studentId);

            return book;
        } else {

            throw new NoSuchElementException("BookServiceImpl");
        }
    }

    @Override
    public Book returnBook(int bookId) {
        Book book = findBookById(bookId);
        if(book != null && !book.isAvailable()){
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
