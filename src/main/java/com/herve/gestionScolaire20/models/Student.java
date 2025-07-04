package com.herve.gestionScolaire20.models;

import com.herve.gestionScolaire20.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student extends AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String lastname;
    private String firstname;
    private String email;
    @Column(unique = true,updatable = false)
    private String registrationNumber;
    private LocalDateTime dateOfBirth;

    @ManyToOne
    private Classroom classroom;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private Set<Grade> grades = new HashSet<>();
    @ManyToOne
    private Parent parent;
}
