package com.example.library001.repository.impl;

import com.example.library001.entity.StudentBook;
import com.example.library001.repository.StudentBookRepositoryService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class StudentBookRepository implements StudentBookRepositoryService {

    private static StudentBookRepository INSTANCE = null;

    public static StudentBookRepository GET_INSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new StudentBookRepository();
        }
        return INSTANCE;
    }

    private List<StudentBook> studentBooksList = new ArrayList<>();

    @Override
    public StudentBook save(int bookId, int studentId) {
        StudentBook studentBookCheck = hasBorrowedStudentBook(bookId);
        if(studentBookCheck == null) {
            studentBookCheck = new StudentBook(studentId, bookId);
            studentBooksList.add(studentBookCheck);

            return studentBookCheck;
        } else {

            return null;
        }
    }

    @Override
    public StudentBook update(int bookId) {
        StudentBook studentBookCheck = hasBorrowedStudentBook(bookId);
        if(studentBookCheck != null) {
            studentBookCheck.setReturnDate(LocalDate.now());

            return studentBookCheck;
        }

        throw new NoSuchElementException("This book is not borrowed");
    }

    @Override
    public List<StudentBook> getStudentBookList() {
        return List.copyOf(studentBooksList);
    }

    private StudentBook hasBorrowedStudentBook(int bookId) {
        for (StudentBook studentBook : studentBooksList) {
            if (studentBook.getBookId() == bookId && studentBook.getReturnDate() == null) {

                return studentBook;
            }
        }

        return null;
    }
}
