package com.example.library001.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
//    private boolean isAvailable;

//    public Book(Long id, String title, String author) {
//        this.id = id;
//        this.title = title;
//        this.author = author;
//        this.isAvailable = true;
//    }


//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
}
