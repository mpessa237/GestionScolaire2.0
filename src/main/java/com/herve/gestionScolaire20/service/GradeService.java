package com.herve.gestionScolaire20.service;

import com.herve.gestionScolaire20.dto.GradeReqDTO;
import com.herve.gestionScolaire20.dto.GradeRespDTO;
import com.herve.gestionScolaire20.mapper.GradeMapper;
import com.herve.gestionScolaire20.models.Course;
import com.herve.gestionScolaire20.models.Grade;
import com.herve.gestionScolaire20.models.Student;
import com.herve.gestionScolaire20.repository.CourseRepo;
import com.herve.gestionScolaire20.repository.GradeRepo;
import com.herve.gestionScolaire20.repository.StudentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    private final GradeRepo gradeRepo;
    private final GradeMapper gradeMapper;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    public GradeService(GradeRepo gradeRepo, GradeMapper gradeMapper, StudentRepo studentRepo, CourseRepo courseRepo) {
        this.gradeRepo = gradeRepo;
        this.gradeMapper = gradeMapper;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public GradeRespDTO createGrade(GradeReqDTO gradeReqDTO){
        Student student = studentRepo.findById(gradeReqDTO.getStudentId())
                .orElseThrow(()->new EntityNotFoundException("student not found!!"));
        Course course = courseRepo.findById(gradeReqDTO.getCourseId())
                .orElseThrow(()->new EntityNotFoundException("course not found!!"));

        if (gradeRepo.existsByStudentAndCourse(student,course)){
            throw new IllegalStateException(
                    String.format("Student %s (%d) already has a grade for course %s (%d)",
                            student.getLastname(),student.getStudentId(),
                            student.getFirstname(),course.getCourseId())
            );
        }
        if (gradeReqDTO.getValue() < 0 || gradeReqDTO.getValue() > 20){
            throw new IllegalArgumentException("The score must be between 0 and 20");
        }
        Grade grade = gradeMapper.toEntity(gradeReqDTO);
        grade.setStudent(student);
        grade.setCourse(course);

        Grade savedGrade = gradeRepo.save(grade);
        return gradeMapper.toDto(savedGrade);
    }

    public List<Grade> findAll(){
        return this.gradeRepo.findAll();
    }
    public GradeRespDTO findById(Integer gradeId){
        Grade grade = gradeRepo.findById(gradeId)
                .orElseThrow(()->new EntityNotFoundException("garde not found!!"));
        return this.gradeMapper.toDto(grade);
    }
    public Grade update(Grade grade,Integer gradeId){
        Optional<Grade> gradeOptional = gradeRepo.findById(gradeId);

        if (gradeOptional.isEmpty())
            throw new EntityNotFoundException("grade not found!!");
        if (grade.getValue()!=null)
            gradeOptional.get().setValue(grade.getValue());
        if (grade.getAppreciation()!=null)
            gradeOptional.get().setAppreciation(grade.getAppreciation());

        return this.gradeRepo.saveAndFlush(gradeOptional.get());
    }

    public void delete(Integer gradeId){
        gradeRepo.findById(gradeId)
                .orElseThrow(()->new EntityNotFoundException("grade not found!!"));
        gradeRepo.deleteById(gradeId);
    }
}
