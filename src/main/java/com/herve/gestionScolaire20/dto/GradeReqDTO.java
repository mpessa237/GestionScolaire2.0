package com.herve.gestionScolaire20.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeReqDTO {
    @Positive(message = "Grade value should be positive")
    private Double value;
    private String appreciation;
    private Integer studentId;
    private Integer courseId;
}
