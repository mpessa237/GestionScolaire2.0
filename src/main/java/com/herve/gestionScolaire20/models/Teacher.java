package com.herve.gestionScolaire20.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "teacher")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "lastname")
public class Teacher extends AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;
    private String lastname;
    private String firstname;
    private String email;
    private String phone;
    private String address;

    @ManyToMany
    @JoinTable(
            name = "teacher_course",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    //@JsonBackReference
    //Placé sur l'enfant ou le côté "inverse". Ce côté sera ignoré lors de la sérialisation pour éviter la boucle.
    //mais la liste des cours ne sera pas serialiser
    private Set<Course> courses = new HashSet<>();
}
