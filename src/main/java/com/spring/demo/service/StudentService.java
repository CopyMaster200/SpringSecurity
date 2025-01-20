package com.spring.demo.service;

import com.spring.demo.model.Student;
import com.spring.demo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    public List<Student> fetchStudents() {
        return studentRepo.findAll();
    }

}
