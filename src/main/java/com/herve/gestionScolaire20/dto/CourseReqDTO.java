package com.herve.gestionScolaire20.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseReqDTO {
    @NotBlank(message = "name is required")
    private String name;
    private Double coefficient;

}
