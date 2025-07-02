package com.herve.gestionScolaire20.mapper;

import com.herve.gestionScolaire20.dto.CourseReqDTO;
import com.herve.gestionScolaire20.dto.CourseRespDTO;
import com.herve.gestionScolaire20.models.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseReqDTO courseReqDTO){
        Course course = new Course();
        course.setName(courseReqDTO.getName());
        course.setCoefficient(courseReqDTO.getCoefficient());


        return course;
    }

    public CourseRespDTO toDto(Course course){
        CourseRespDTO courseRespDTO = new CourseRespDTO();
        courseRespDTO.setCourseId(course.getCourseId());
        courseRespDTO.setName(course.getName());
        courseRespDTO.setCoefficient(course.getCoefficient());

        return courseRespDTO;
    }
}
