package com.example.library001.repository;

import com.example.library001.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    //TODO add search by name (Query)

}
