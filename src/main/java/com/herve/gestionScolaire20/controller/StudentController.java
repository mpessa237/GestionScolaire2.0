package com.herve.gestionScolaire20.controller;

import com.herve.gestionScolaire20.common.ApiResponse;
import com.herve.gestionScolaire20.dto.StudentReqDTO;
import com.herve.gestionScolaire20.models.Student;
import com.herve.gestionScolaire20.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody @Validated StudentReqDTO studentReqDTO){
        var student = studentService.create(studentReqDTO);
        return ResponseEntity.ok(new ApiResponse("student created successfully",student.getLastname()));
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAll(){
        return ResponseEntity.ok(this.studentService.findAll());
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> update(@RequestBody Student student,@PathVariable Integer studentId){
        return ResponseEntity.ok(this.studentService.update(student, studentId));
    }
}
