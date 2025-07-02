package com.herve.gestionScolaire20.models;

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
@Table(name = "classroom")
public class Classroom extends AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classroomId;
    private String className;

    @OneToMany(mappedBy = "classroom",cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();
    @ManyToMany(mappedBy = "classrooms")
    private Set<Course> courses = new HashSet<>();
}
