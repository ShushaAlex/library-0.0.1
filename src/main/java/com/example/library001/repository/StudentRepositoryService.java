package com.example.library001.repository;

import com.example.library001.entity.Student;

import java.util.List;

public interface StudentRepositoryService {
    Student save(Student student);
    Student findStudentById(int id);
    List<Student> getStudentsList();
}
