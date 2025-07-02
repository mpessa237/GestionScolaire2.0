package com.herve.gestionScolaire20.dto;

import com.herve.gestionScolaire20.models.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRespDTO {
    private Integer teacherId;
    private String lastname;
    private String firstname;
    private Integer courseId;
    private Set<Course> courses = new HashSet<>();

}
