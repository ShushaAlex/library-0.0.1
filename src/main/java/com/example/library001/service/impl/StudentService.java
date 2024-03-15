package com.example.library001.service.impl;


import com.example.library001.entity.Student;
import com.example.library001.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findByID(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
