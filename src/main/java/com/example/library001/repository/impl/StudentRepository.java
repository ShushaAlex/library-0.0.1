package com.example.library001.repository.impl;

import com.example.library001.entity.Student;
import com.example.library001.repository.StudentRepositoryService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentRepository implements StudentRepositoryService {

    private static StudentRepository INSTANCE = null;
    public static StudentRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StudentRepository();
        }
        return INSTANCE;
    }

    List<Student> studentList = new ArrayList<>();
    @Override
    public Student save(Student student) {
        Student studentCheck = findStudentById(student.getId());
        if (studentCheck == null) {
            studentList.add(student);
            return student;
        }
        return studentCheck;
    }

    @Override
    public Student findStudentById(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> getStudentsList() {
        return List.copyOf(studentList);
    }
}
