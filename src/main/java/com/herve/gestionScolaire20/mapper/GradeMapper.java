package com.herve.gestionScolaire20.mapper;

import com.herve.gestionScolaire20.dto.GradeReqDTO;
import com.herve.gestionScolaire20.dto.GradeRespDTO;
import com.herve.gestionScolaire20.models.Grade;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper {

    public Grade toEntity(GradeReqDTO gradeReqDTO){
        Grade grade = new Grade();
        grade.setValue(gradeReqDTO.getValue());
        grade.setAppreciation(gradeReqDTO.getAppreciation());

        return grade;
    }

    public GradeRespDTO toDto(Grade grade){
        GradeRespDTO gradeRespDTO = new GradeRespDTO();
        gradeRespDTO.setValue(grade.getValue());
        gradeRespDTO.setGradeId(grade.getGradeId());
        gradeRespDTO.setAppreciation(grade.getAppreciation());
        gradeRespDTO.setCourseId(grade.getCourse().getCourseId());
        gradeRespDTO.setStudentId(grade.getStudent().getStudentId());

        return gradeRespDTO;
    }
}
