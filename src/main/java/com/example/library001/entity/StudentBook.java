package com.example.library001.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class StudentBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long studentId;
    private Long bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

//    public StudentBook(int studentId, int bookId) {
//        this.studentId = studentId;
//        this.bookId = bookId;
//        this.borrowDate = LocalDate.now();
//    }
}
