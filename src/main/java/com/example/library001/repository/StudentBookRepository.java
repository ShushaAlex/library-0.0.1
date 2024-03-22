package com.example.library001.repository;

import com.example.library001.entity.StudentBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentBookRepository extends JpaRepository<StudentBook, Long> {

}
