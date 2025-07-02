package com.herve.gestionScolaire20.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.herve.gestionScolaire20.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course extends AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    private String name;
    @Column(nullable = false)
    private Double coefficient;

    @ManyToMany
    @JoinTable(
            name = "course_classroom",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id")
    )
    private Set<Classroom> classrooms = new HashSet<>();
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private Set<Grade> grades = new HashSet<>();
    @ManyToMany(mappedBy = "courses")
    private Set<Teacher> teachers = new HashSet<>();

}
