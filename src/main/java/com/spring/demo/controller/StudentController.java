package com.spring.demo.controller;

import com.spring.demo.model.Student;
import com.spring.demo.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.fetchStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.fetchStudentById(id);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable int id) {
        studentService.deleteStudentById(id);
    }

    @DeleteMapping("/cache/clear")
    public void clearAllStudentsCache() {
        studentService.clearAllStudentsCache();
    }

    @PostMapping("/save-update")
    public Student saveAndUpdateCache(@RequestBody Student student) {
        return studentService.saveAndUpdateCache(student);
    }

    @GetMapping("/conditional/{id}")
    public Student getStudentConditionally(@PathVariable int id) {
        return studentService.fetchStudentConditionally(id);
    }

    @GetMapping("/sync/{id}")
    public Student getStudentWithSync(@PathVariable int id) {
        return studentService.fetchStudentWithSync(id);
    }
}
