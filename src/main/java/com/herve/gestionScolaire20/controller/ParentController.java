package com.herve.gestionScolaire20.controller;

import com.herve.gestionScolaire20.common.ApiResponse;
import com.herve.gestionScolaire20.dto.ParentReqDTO;
import com.herve.gestionScolaire20.dto.ParentRespDTO;
import com.herve.gestionScolaire20.models.Parent;
import com.herve.gestionScolaire20.service.ParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parent")
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody @Validated ParentReqDTO parentReqDTO){
        var parent = parentService.create(parentReqDTO);
        return ResponseEntity.ok(new ApiResponse("parent created successfully",parent.getLastname()));
    }

    @GetMapping
    public ResponseEntity<List<Parent>> getAll(){
        return ResponseEntity.ok(this.parentService.findAll());
    }
}
