package com.example.library001.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
//@Table(name = "book") если название тадлицы отличается от сущности
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private boolean isAvailable = true;
}
