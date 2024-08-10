package com.example.lab.repositories;

import com.example.lab.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentById(long id);

    List<Student> findByNameContainingIgnoreCase(String name);
}
