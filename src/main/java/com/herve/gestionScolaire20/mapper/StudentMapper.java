package com.herve.gestionScolaire20.mapper;

import com.herve.gestionScolaire20.dto.StudentReqDTO;
import com.herve.gestionScolaire20.dto.StudentRespDTO;
import com.herve.gestionScolaire20.models.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentReqDTO studentReqDTO){
        Student student = new Student();
        student.setLastname(studentReqDTO.getLastname());
        student.setFirstname(studentReqDTO.getFirstname());
        student.setEmail(studentReqDTO.getEmail());
        student.setDateOfBirth(studentReqDTO.getDateOfBirth());

        return student;
    }

    public StudentRespDTO toDto(Student student){
        StudentRespDTO studentRespDTO = new StudentRespDTO();
        studentRespDTO.setStudentId(student.getStudentId());
        studentRespDTO.setLastname(student.getLastname());
        studentRespDTO.setFirstname(student.getFirstname());
        studentRespDTO.setDateOfBirth(student.getDateOfBirth());
        studentRespDTO.setClassroomId(student.getClassroom().getClassroomId());

        return studentRespDTO;
    }
}
