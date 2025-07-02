package com.herve.gestionScolaire20.dto;

import com.herve.gestionScolaire20.models.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRespDTO {
    private Integer courseId;
    private String name;
    private Double coefficient;
    private Set<Teacher> teachers = new HashSet<>();

}
