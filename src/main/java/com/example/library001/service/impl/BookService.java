package com.example.library001.service.impl;

import com.example.library001.entity.Book;
import com.example.library001.entity.StudentBook;
import com.example.library001.repository.BookRepository;
import com.example.library001.repository.StudentBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StudentBookRepository studentBookRepository;

    public List<Book> findAll() {

        return bookRepository.findAll();
    }

    public List<StudentBook> findAllStudentBook() {

        return studentBookRepository.findAll();
    }

    public Book findByID(Long id) {

        return bookRepository.findById(id).orElse(null);
    }

    public Book findByTitle(String title) throws Exception {
        List<Book> books = findAll();
        Book book = books.stream()
                .filter(x -> x.getTitle().equals(title))
                .findFirst().orElse(null);
        if (book != null) {

            return book;
        } else {
            throw new Exception("There is no such book");
        }
    }

    public Book save(Book book) {

        return bookRepository.save(book);
    }

    public StudentBook borrowBook(Long bookID, Long studentID) throws Exception {
        if (bookIsAvailable(bookID)) {
            StudentBook studentBook = new StudentBook(studentID, bookID, LocalDate.now());
            studentBookRepository.save(studentBook);
            updateIsAvailable(bookID, false);

            return studentBook;
        } else {
            throw new Exception("This book is not available");
        }
    }

    public StudentBook borrowBook(String bookTitle, Long studentID) throws Exception {
        Book book = findByTitle(bookTitle);
        if (bookIsAvailable(book.getId())) {
            StudentBook studentBook = new StudentBook(studentID, book.getId(), LocalDate.now());
            studentBookRepository.save(studentBook);
            updateIsAvailable(book.getId(), false);

            return studentBook;
        } else {
            throw new Exception("This book is not available");
        }
    }

    public StudentBook returnBook(Long bookID) throws Exception {
        List<StudentBook> studentBooks = findStudentBookByBookID(bookID);
        StudentBook studentBook = getStudentBookWithoutReturnDate(studentBooks);
        if (studentBook == null) {
            throw new Exception("This book is not borrowed");
        }
        studentBook.setReturnDate(LocalDate.now());
        updateIsAvailable(bookID, true);

        return studentBookRepository.save(studentBook);
    }

    public StudentBook returnBook(String bookTitle) throws Exception {
        Book book = findByTitle(bookTitle);
        List<StudentBook> studentBooks = findStudentBookByBookID(book.getId());
        StudentBook studentBook = getStudentBookWithoutReturnDate(studentBooks);
        if (studentBook == null) {
            throw new Exception("This book is not borrowed");
        }
        studentBook.setReturnDate(LocalDate.now());
        updateIsAvailable(book.getId(), true);

        return studentBookRepository.save(studentBook);
    }

    private boolean bookIsAvailable(Long bookID) {
        return findByID(bookID).isAvailable();
    }

    private Book updateIsAvailable(Long bookID, boolean isAvailable) {
        Book bookToUpdate = findByID(bookID);
        bookToUpdate.setAvailable(isAvailable);

        return save(bookToUpdate);
    }

    private List<StudentBook> findStudentBookByBookID(Long bookID) {
        List<StudentBook> allStudentBooks = studentBookRepository.findAll();

        return allStudentBooks.stream()
                .filter(x -> x.getBookId().equals(bookID))
                .toList();
    }

    private StudentBook getStudentBookWithoutReturnDate(List<StudentBook> studentBooks) {

        return studentBooks.stream()
                .filter(x -> x.getReturnDate() == null)
                .findAny().orElse(null);
    }
}