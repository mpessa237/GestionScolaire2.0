package com.herve.gestionScolaire20.controller;

import com.herve.gestionScolaire20.common.ApiResponse;
import com.herve.gestionScolaire20.dto.CourseReqDTO;
import com.herve.gestionScolaire20.dto.CourseRespDTO;
import com.herve.gestionScolaire20.models.Course;
import com.herve.gestionScolaire20.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody @Validated CourseReqDTO courseReqDTO){
        var course = courseService.createCourse(courseReqDTO);
        return ResponseEntity.ok(new ApiResponse("course created successfully",course.getName()));
    }
    @GetMapping
    public ResponseEntity<List<Course>> getAll(){
        return ResponseEntity.ok(this.courseService.findAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CourseRespDTO> getByName(@PathVariable String name){
        return ResponseEntity.ok(this.courseService.getCourseByName(name));
    }
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseRespDTO> getById(@PathVariable Integer courseId){
        return ResponseEntity.ok(this.courseService.getById(courseId));
    }
    @PutMapping("/{courseId}")
    public ResponseEntity<Course> update(@RequestBody Course course,@PathVariable Integer courseId){
        return ResponseEntity.ok(this.courseService.update(course, courseId));
    }
    @DeleteMapping("/{courseId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer courseId){
        courseService.delete(courseId);
        return ResponseEntity.ok(new ApiResponse("course delete successfully",null));
    }
}

