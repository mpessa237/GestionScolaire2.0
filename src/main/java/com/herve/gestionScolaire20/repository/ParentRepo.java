package com.herve.gestionScolaire20.repository;

import com.herve.gestionScolaire20.models.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepo extends JpaRepository<Parent,Integer> {
    Optional<Parent> findByLastname(String lastname);
}
