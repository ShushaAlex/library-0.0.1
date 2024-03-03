package com.example.library001.repository;

import com.example.library001.entity.StudentBook;

import java.util.List;

public interface StudentBookRepositoryService {
    StudentBook save(int bookId, int studentId);
    StudentBook update(int bookId);
    List<StudentBook> getStudentBookList();
}
