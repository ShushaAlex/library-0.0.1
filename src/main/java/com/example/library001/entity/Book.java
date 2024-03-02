package com.example.library001.entity;

import lombok.Data;

@Data
public class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;
}
