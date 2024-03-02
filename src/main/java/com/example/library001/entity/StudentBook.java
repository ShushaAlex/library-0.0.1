package com.example.library001.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentBook {
    private int studentId;
    private int bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

}
