package com.herve.gestionScolaire20.repository;

import com.herve.gestionScolaire20.models.Course;
import com.herve.gestionScolaire20.models.Grade;
import com.herve.gestionScolaire20.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepo extends JpaRepository<Grade,Integer> {
    boolean existsByStudentAndCourse(Student student, Course course);
}
