package com.herve.gestionScolaire20.repository;

import com.herve.gestionScolaire20.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {

    Optional<Course> findByName(String name);
}
