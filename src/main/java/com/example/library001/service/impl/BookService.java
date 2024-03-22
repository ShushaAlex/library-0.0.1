package com.example.library001.service.impl;

import com.example.library001.entity.Book;
import com.example.library001.entity.StudentBook;
import com.example.library001.exception.BookNotAvailableException;
import com.example.library001.exception.BookNotBorrowedException;
import com.example.library001.exception.NoBookException;
import com.example.library001.repository.BookRepository;
import com.example.library001.repository.StudentBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.library001.exception.constance.CustomMessageException.NOT_AVAILABLE;
import static com.example.library001.exception.constance.CustomMessageException.NOT_BORROWED;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StudentBookRepository studentBookRepository;

    //TODO refactor methods with Query
    public List<Book> findAll() {

        return bookRepository.findAll();
    }

    public List<StudentBook> findAllStudentBook() {

        return studentBookRepository.findAll();
    }

    //TODO add custom message for exception
    public Book findByID(Long id) throws NoBookException {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null){
            return book;
        } else {
            throw new NoBookException("There is no book with id '" + id + "' in the lib");
        }
    }

    public Book findByTitle(String title) throws NoBookException {
        Book book = bookRepository.findByTitle(title);
        if (book != null){
            return book;
        } else {
            throw new NoBookException("There is no book '" + title + "' in the lib");
        }
    }

    public Book save(Book book) {

        return bookRepository.save(book);
    }

    public StudentBook borrowBook(Long bookID, Long studentID) throws BookNotAvailableException, NoBookException {
        if (bookIsAvailable(bookID)) {
            StudentBook studentBook = new StudentBook(studentID, bookID, LocalDate.now());
            studentBookRepository.save(studentBook);
            updateIsAvailable(bookID, false);

            return studentBook;
        } else {
            throw new BookNotAvailableException(NOT_AVAILABLE.getCustomMessage());
        }
    }

    public StudentBook borrowBook(String bookTitle, Long studentID) throws BookNotAvailableException, NoBookException {
        Book book = findByTitle(bookTitle);
        if (bookIsAvailable(book.getId())) {
            StudentBook studentBook = new StudentBook(studentID, book.getId(), LocalDate.now());
            studentBookRepository.save(studentBook);
            updateIsAvailable(book.getId(), false);

            return studentBook;
        } else {
            throw new BookNotAvailableException(NOT_AVAILABLE.getCustomMessage());
        }
    }

    public StudentBook returnBook(Long bookID) throws BookNotBorrowedException, NoBookException {
        List<StudentBook> studentBooks = findStudentBookByBookID(bookID);
        StudentBook studentBook = getStudentBookWithoutReturnDate(studentBooks);
        if (studentBook == null) {
            throw new BookNotBorrowedException(NOT_BORROWED.getCustomMessage());
        }
        studentBook.setReturnDate(LocalDate.now());
        updateIsAvailable(bookID, true);

        return studentBookRepository.save(studentBook);
    }

    public StudentBook returnBook(String bookTitle) throws BookNotBorrowedException, NoBookException {
        Book book = findByTitle(bookTitle);
        List<StudentBook> studentBooks = findStudentBookByBookID(book.getId());
        StudentBook studentBook = getStudentBookWithoutReturnDate(studentBooks);
        if (studentBook == null) {
            throw new BookNotBorrowedException(NOT_BORROWED.getCustomMessage());
        }
        studentBook.setReturnDate(LocalDate.now());
        updateIsAvailable(book.getId(), true);

        return studentBookRepository.save(studentBook);
    }

    private boolean bookIsAvailable(Long bookID) throws NoBookException {
        return findByID(bookID).isAvailable();
    }

    //TODO change to Query update
    private Book updateIsAvailable(Long bookID, boolean isAvailable) throws NoBookException {
        bookRepository.updateIsAvailable(bookID, isAvailable);
        return findByID(bookID);
    }

    //TODO change to Query update
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