package com.herve.gestionScolaire20.models;

import com.herve.gestionScolaire20.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grade")
public class Grade extends AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gradeId;
    private Double value;
    private String appreciation;

    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;
    @ManyToOne
    @JoinColumn(name = "student_id",nullable = false)
    private Student student;

}
