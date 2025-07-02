package com.herve.gestionScolaire20.service;

import com.herve.gestionScolaire20.dto.CourseReqDTO;
import com.herve.gestionScolaire20.dto.CourseRespDTO;
import com.herve.gestionScolaire20.mapper.CourseMapper;
import com.herve.gestionScolaire20.models.Course;
import com.herve.gestionScolaire20.models.Teacher;
import com.herve.gestionScolaire20.repository.CourseRepo;
import com.herve.gestionScolaire20.repository.TeacherRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepo courseRepo;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepo courseRepo, CourseMapper courseMapper) {
        this.courseRepo = courseRepo;
        this.courseMapper = courseMapper;
    }

    public CourseRespDTO createCourse(CourseReqDTO courseReqDTO){
        Course course = courseMapper.toEntity(courseReqDTO);
        Course savedCourse = courseRepo.save(course);

        return this.courseMapper.toDto(savedCourse);
    }

    public List<Course> findAll(){
        return this.courseRepo.findAll();
    }

    public CourseRespDTO getCourseByName(String name){
        Course course = courseRepo.findByName(name)
                .orElseThrow(()->new EntityNotFoundException("course not found!!"));
        return this.courseMapper.toDto(course);
    }

    public CourseRespDTO getById(Integer courseId){
        Course course = courseRepo.findById(courseId)
                .orElseThrow(()->new EntityNotFoundException("course not found!!"));
        return this.courseMapper.toDto(course);
    }

    public Course update(Course course,Integer courseId){
        Optional<Course> courseOptional = courseRepo.findById(courseId);

        if (courseOptional.isEmpty())
            throw new EntityNotFoundException("course not found!!");

        if (course.getName()!=null)
            courseOptional.get().setName(course.getName());
        if (course.getCoefficient()!=null)
            courseOptional.get().setCoefficient(course.getCoefficient());

        return this.courseRepo.saveAndFlush(courseOptional.get());
    }

    public void delete(Integer courseId){
        courseRepo.findById(courseId)
                .orElseThrow(()->new EntityNotFoundException("course not found!!"));
        courseRepo.deleteById(courseId);

    }



}
