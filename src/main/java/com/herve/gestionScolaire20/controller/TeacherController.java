package com.herve.gestionScolaire20.controller;

import com.herve.gestionScolaire20.common.ApiResponse;
import com.herve.gestionScolaire20.dto.TeacherReqDTO;
import com.herve.gestionScolaire20.dto.TeacherRespDTO;
import com.herve.gestionScolaire20.models.Teacher;
import com.herve.gestionScolaire20.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody @Validated TeacherReqDTO teacherReqDTO){
        var teacher = teacherService.createTeacher(teacherReqDTO);
        return ResponseEntity.ok(new ApiResponse("teacher created successfully",teacher.getLastname()));
    }
    @GetMapping
    public ResponseEntity<List<Teacher>> getAll(){
        return ResponseEntity.ok(this.teacherService.findAll());
    }
    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherRespDTO> getById(@PathVariable Integer teacherId){
        return ResponseEntity.ok(this.teacherService.findById(teacherId));
    }
    @GetMapping("/{lastname}")
    public ResponseEntity<TeacherRespDTO> getByLastname(@PathVariable String lastname){
        return ResponseEntity.ok(this.teacherService.findByLastname(lastname));
    }
    @PutMapping("/{teacherId}")
    public ResponseEntity<Teacher> update(@RequestBody Teacher teacher,@PathVariable Integer teacherId){
        return ResponseEntity.ok(this.teacherService.update(teacher, teacherId));
    }
    @DeleteMapping("/{teacherId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer teacherId){
        teacherService.delete(teacherId);
        return ResponseEntity.ok(new ApiResponse("teacher deleted successfully",null));
    }
}
