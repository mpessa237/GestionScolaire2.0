package com.herve.gestionScolaire20.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentRespDTO {
    private Integer parentId;
    private String lastname;
    private String firstname;
    private String email;
    private String address;
    private String phone;
    private Integer studentId;
}
