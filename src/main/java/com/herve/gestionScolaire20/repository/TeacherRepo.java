package com.herve.gestionScolaire20.repository;

import com.herve.gestionScolaire20.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Integer> {

    Optional<Teacher> findByLastname(String lastname);
}
