package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudent();

    void saveStudent(Student student);

    void deleteStudent(Long id);

    Optional<Student> findStudentById(Long id);

    Student getOne(Long id);

    List<Student> getStudentId(String studentId);

    List<Student> getStudentEmail(String email);

    List<Student> getStudentPhone(String phone);
}
