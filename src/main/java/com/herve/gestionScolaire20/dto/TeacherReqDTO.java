package com.herve.gestionScolaire20.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherReqDTO {
    @NotBlank(message = "lastname is required")
    @NotEmpty(message = "lastname is mandatory")
    private String lastname;
    private String firstname;
    @NotEmpty(message = "email is mandatory")
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotEmpty(message = "phone is mandatory")
    @NotBlank(message = "phone is mandatory")
    private String phone;
    private String address;
    private Integer courseId;
}
