package com.herve.gestionScolaire20.controller;

import com.herve.gestionScolaire20.common.ApiResponse;
import com.herve.gestionScolaire20.models.Classroom;
import com.herve.gestionScolaire20.service.ClassroomService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody @Validated Classroom classroom){
        var classroom1 = classroomService.create(classroom);
        return ResponseEntity.ok(new ApiResponse("classroom created successfully",classroom1.getClassName()));
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getAll(){
        return ResponseEntity.ok(this.classroomService.findAll());
    }

    @DeleteMapping("/{classroomId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer classroomId){
        classroomService.delete(classroomId);
        return ResponseEntity.ok(new ApiResponse("classroom deleted successfully",null));
    }

}
