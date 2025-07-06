package com.herve.gestionScolaire20.service;

import com.herve.gestionScolaire20.dto.TeacherReqDTO;
import com.herve.gestionScolaire20.dto.TeacherRespDTO;
import com.herve.gestionScolaire20.mapper.TeacherMapper;
import com.herve.gestionScolaire20.models.Course;
import com.herve.gestionScolaire20.models.Teacher;
import com.herve.gestionScolaire20.repository.CourseRepo;
import com.herve.gestionScolaire20.repository.TeacherRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;



@Service
public class TeacherService {

    private final TeacherRepo teacherRepo;
    private final TeacherMapper teacherMapper;
    private final CourseRepo courseRepo;

    public TeacherService(TeacherRepo teacherRepo, TeacherMapper teacherMapper, CourseRepo courseRepo) {
        this.teacherRepo = teacherRepo;
        this.teacherMapper = teacherMapper;
        this.courseRepo = courseRepo;
    }

    public TeacherRespDTO createTeacher(TeacherReqDTO teacherReqDTO){

        Course course = courseRepo.findById(teacherReqDTO.getCourseId())
                .orElseThrow(()->new EntityNotFoundException("course not found!!"));

        Teacher teacher = teacherMapper.toEntity(teacherReqDTO);

        // Init la collection si null
        if (teacher.getCourses() == null){
            teacher.setCourses(new HashSet<>());
        }
        teacher.getCourses().add(course);

        // update le côté inverse de la relation (important en ManyToMany)
        if (course.getTeachers() == null){
            course.setTeachers(new HashSet<>());
        }
        course.getTeachers().add(teacher);

        Teacher savedTeacher = teacherRepo.save(teacher);
        courseRepo.save(course);
        return this.teacherMapper.toDto(savedTeacher);
    }

    public List<Teacher> findAll(){
        return this.teacherRepo.findAll();
    }
    public TeacherRespDTO findByLastname(String lastname){
        Teacher teacher = teacherRepo.findByLastname(lastname)
                .orElseThrow(()-> new EntityNotFoundException("teacher not found!!"));
        return this.teacherMapper.toDto(teacher);
    }
    public TeacherRespDTO findById(Integer teacherId){
        Teacher teacher = teacherRepo.findById(teacherId)
                .orElseThrow(()-> new EntityNotFoundException("teacher not found!!"));
        return this.teacherMapper.toDto(teacher);
    }

    public Teacher update(Teacher teacher,Integer teacherId){
        Optional<Teacher> teacherOptional = teacherRepo.findById(teacherId);

        if (teacherOptional.isEmpty())
            throw new EntityNotFoundException("teacher not found!!");
        if (teacher.getLastname()!=null)
            teacherOptional.get().setLastname(teacher.getLastname());
        if (teacher.getFirstname()!=null)
            teacherOptional.get().setFirstname(teacher.getFirstname());
        if (teacher.getPhone()!=null)
            teacherOptional.get().setPhone(teacher.getPhone());
        if (teacher.getEmail()!=null)
            teacherOptional.get().setEmail(teacher.getEmail());
        if (teacher.getAddress()!=null)
            teacherOptional.get().setAddress(teacher.getAddress());

        return this.teacherRepo.saveAndFlush(teacherOptional.get());


    }

    public void delete(Integer teacherId){
        teacherRepo.findById(teacherId)
                .orElseThrow(()->new EntityNotFoundException("teacher not found"));
        teacherRepo.deleteById(teacherId);
    }

}
