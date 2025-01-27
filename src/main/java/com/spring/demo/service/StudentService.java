package com.spring.demo.service;

import com.spring.demo.model.Student;
import com.spring.demo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "students") // Default cache name for all methods
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    /**
     * Add a new student and update the cache.
     *
     * @param student The student to add.
     * @return The saved student.
     */
    @CachePut(key = "#student.id")
    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    /**
     * Fetch all students, caching the result.
     *
     * @return A list of all students.
     */
    @Cacheable
    public List<Student> fetchStudents() {
        return studentRepo.findAll();
    }

    /**
     * Fetch a student by ID, caching the result.
     *
     * @param id The ID of the student.
     * @return The student with the given ID.
     */
    @Cacheable(key = "#id")
    public Student fetchStudentById(int id) {
        return studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    /**
     * Update a student's details and refresh the cache.
     *
     * @param student The student with updated details.
     * @return The updated student.
     */
    @CachePut(key = "#student.id")
    public Student updateStudent(Student student) {
        return studentRepo.save(student);
    }

    /**
     * Delete a student by ID and remove the entry from the cache.
     *
     * @param id The ID of the student to delete.
     */
    @CacheEvict(key = "#id")
    public void deleteStudentById(int id) {
        studentRepo.deleteById(id);
    }

    /**
     * Clear all cache entries for students.
     */
    @CacheEvict(allEntries = true)
    public void clearAllStudentsCache() {
        // This will clear all cache entries for "students"
    }

    /**
     * Save or update a student and handle both cache put and eviction scenarios.
     *
     * @param student The student to save.
     * @return The saved student.
     */
    @CachePut(key = "#student.id")
    @CacheEvict(allEntries = true)
    public Student saveAndUpdateCache(Student student) {
        return studentRepo.save(student);
    }

    /**
     * Fetch a student by ID only if the condition is met.
     * Caches the result if the condition is true.
     *
     * @param id The ID of the student.
     * @return The student with the given ID.
     */
    @Cacheable(key = "#id", condition = "#id > 5")
    public Student fetchStudentConditionally(int id) {
        return studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    /**
     * Fetch a student by ID, ensuring multiple threads do not execute the method simultaneously.
     *
     * @param id The ID of the student.
     * @return The student with the given ID.
     */
    @Cacheable(key = "#id", sync = true)
    public Student fetchStudentWithSync(int id) {
        return studentRepo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }
}


