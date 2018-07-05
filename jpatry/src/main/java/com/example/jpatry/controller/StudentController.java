package com.example.jpatry.controller;

import com.example.jpatry.domain.Student;
import com.example.jpatry.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value="/students")
    public List<Student> studentList(){
        return studentRepository.findAll();
    }

    @RequestMapping(value="/insert")
    public Student addStudent(@RequestParam("sno") Integer sno,@RequestParam("name") String name,@RequestParam("major") String major) {
        Student student = new Student();
        student.setSno(sno);
        student.setName(name);
        student.setMajor(major);

        return studentRepository.save(student);
    }

    @RequestMapping(value="/students/{sno}")
    public Student findStudent(@PathVariable("sno") Integer sno){
        return studentRepository.findById(sno).get();

    }

    @RequestMapping(value="/student/major/{major}")
    public List<Student> getStudentByMajor(@PathVariable("major") String major) {
        return studentRepository.findStudentsByMajor(major);
    }
}
