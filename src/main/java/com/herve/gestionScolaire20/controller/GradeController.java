package com.herve.gestionScolaire20.controller;

import com.herve.gestionScolaire20.common.ApiResponse;
import com.herve.gestionScolaire20.dto.GradeReqDTO;
import com.herve.gestionScolaire20.dto.GradeRespDTO;
import com.herve.gestionScolaire20.models.Grade;
import com.herve.gestionScolaire20.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/grade")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    public ResponseEntity<ApiResponse> save(@RequestBody @Validated GradeReqDTO gradeReqDTO){
        var grade = gradeService.createGrade(gradeReqDTO);
        return ResponseEntity.ok(new ApiResponse("grade create successfully",grade.getValue()));
    }

    public ResponseEntity<List<Grade>> getAll(){
        return ResponseEntity.ok(this.gradeService.findAll());
    }

    public ResponseEntity<GradeRespDTO> getById(@PathVariable Integer gradeId){
        return ResponseEntity.ok(this.gradeService.findById(gradeId));
    }
    public ResponseEntity<Grade> update(@RequestBody @Validated Grade grade,@PathVariable Integer gradeId){
        return ResponseEntity.ok(this.gradeService.update(grade, gradeId));
    }

    public ResponseEntity<ApiResponse> delete(@PathVariable Integer gradeId){
        gradeService.delete(gradeId);
        return ResponseEntity.ok(new ApiResponse("grade deleted successfully",null));
    }
}
