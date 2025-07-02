package com.herve.gestionScolaire20.mapper;

import com.herve.gestionScolaire20.dto.TeacherReqDTO;
import com.herve.gestionScolaire20.dto.TeacherRespDTO;
import com.herve.gestionScolaire20.models.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

    public Teacher toEntity(TeacherReqDTO teacherReqDTO){

        Teacher teacher = new Teacher();
        teacher.setFirstname(teacherReqDTO.getFirstname());
        teacher.setLastname(teacherReqDTO.getLastname());
        teacher.setEmail(teacherReqDTO.getEmail());
        teacher.setPhone(teacherReqDTO.getPhone());
        teacher.setAddress(teacherReqDTO.getAddress());

        return teacher;
    }

    public TeacherRespDTO toDto(Teacher teacher){
        TeacherRespDTO teacherRespDTO = new TeacherRespDTO();
        teacherRespDTO.setLastname(teacher.getLastname());
        teacherRespDTO.setFirstname(teacher.getFirstname());
        teacherRespDTO.setTeacherId(teacher.getTeacherId());

        return teacherRespDTO;
    }
}
