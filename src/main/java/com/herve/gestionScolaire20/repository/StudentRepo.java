package com.herve.gestionScolaire20.repository;

import com.herve.gestionScolaire20.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
    Optional<Student> findByLastname(String lastname);
    boolean existsByEmail(String email);
    boolean existsByRegistrationNumber(String registrationNumber);
    //Compter les étudiants créés aujourd’hui via
    @Query("SELECT COUNT(s) FROM Student s WHERE FUNCTION('DATE', s.createdDate) = :date")
    long countByCreatedDate(@Param("date") LocalDate date);

}
