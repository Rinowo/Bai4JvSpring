package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {
    List<Student> findByStudentId(String studentId);
    List<Student> findByEmail(String email);
    List<Student> findByPhone(String phone);
}
