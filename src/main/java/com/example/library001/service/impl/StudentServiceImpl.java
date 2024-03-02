package com.example.library001.service.impl;

import com.example.library001.entity.Student;
import com.example.library001.repository.impl.StudentRepository;
import com.example.library001.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    //@Autowired
    private StudentRepository studentRepository;
    @Override
    public Student save(Student student) {

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentsList() {

        return studentRepository.getStudentsList();
    }

    @Override
    public Student findStudentById(int id) {

        return studentRepository.findStudentById(id);
    }
}
