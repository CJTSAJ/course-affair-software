package com.example.jpatry.repository;

import com.example.jpatry.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer>{
    public List<Student> findStudentsByMajor(String major);
}
