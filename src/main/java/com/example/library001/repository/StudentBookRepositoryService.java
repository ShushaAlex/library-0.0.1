package com.example.library001.repository;

import com.example.library001.entity.StudentBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentBookRepositoryService extends JpaRepository<StudentBook, Long> {

}
