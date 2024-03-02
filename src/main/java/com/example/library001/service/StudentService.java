package com.example.library001.service;

import com.example.library001.entity.Student;

import java.util.List;

public interface StudentService {
    Student save(Student student);

    List<Student> getStudentsList();

    Student findStudentById(int id);

}
