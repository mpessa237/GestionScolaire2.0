package com.herve.gestionScolaire20.repository;

import com.herve.gestionScolaire20.models.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassroomRepo extends JpaRepository<Classroom,Integer> {

    Optional<Classroom> findByClassName(String className);
    boolean existsByClassName(String className);
}
