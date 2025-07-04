package com.herve.gestionScolaire20.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRespDTO {
    private Integer studentId;
    private String lastname;
    private String firstname;
    private String registrationNumber;
    private LocalDateTime dateOfBirth;
    private Integer classroomId;
}
