package com.yugmehta.auth_service.controller;

import com.yugmehta.auth_service.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>(List.of(
            new Student(1,25,"Yug"),
            new Student(2,21,"Amar"),
            new Student(3,35,"Amita")
    ));

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        students.add(student);
        return new ResponseEntity<>("Added", HttpStatus.OK);
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrf(CsrfToken token){
        return token;
    }
}
