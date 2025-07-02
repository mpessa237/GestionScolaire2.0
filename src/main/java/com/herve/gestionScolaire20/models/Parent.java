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
@Table(name = "parent")
public class Parent extends AbstractEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer parentId;
    private String lastname;
    private String firstname;
    private String email;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();
}
