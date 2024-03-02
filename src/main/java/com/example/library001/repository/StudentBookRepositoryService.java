package com.example.library001.repository;

import com.example.library001.entity.StudentBook;

public interface StudentBookRepositoryService {
    StudentBook save(int bookId, int studentId, boolean isBorrow);
    StudentBook update(int bookId);
}
