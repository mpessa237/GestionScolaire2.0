package com.herve.gestionScolaire20.service;

import com.herve.gestionScolaire20.dto.ParentReqDTO;
import com.herve.gestionScolaire20.dto.ParentRespDTO;
import com.herve.gestionScolaire20.mapper.ParentMapper;
import com.herve.gestionScolaire20.models.Parent;
import com.herve.gestionScolaire20.models.Student;
import com.herve.gestionScolaire20.repository.ParentRepo;
import com.herve.gestionScolaire20.repository.StudentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
public class ParentService {
    private final ParentRepo parentRepo;
    private final StudentRepo studentRepo;
    private final ParentMapper parentMapper;

    public ParentService(ParentRepo parentRepo, StudentRepo studentRepo, ParentMapper parentMapper) {
        this.parentRepo = parentRepo;
        this.studentRepo = studentRepo;
        this.parentMapper = parentMapper;
    }

    @Transactional
    public ParentRespDTO create(ParentReqDTO parentReqDTO){
        Student student = studentRepo.findById(parentReqDTO.getStudentId())
                .orElseThrow(()->new EntityNotFoundException("student not found!!"));

        Parent parent = parentMapper.toEntity(parentReqDTO);
        //  Définir le parent sur l'objet étudiant (côté propriétaire de la relation)
        // Ajouter l'étudiant à la collection du parent (pour maintenir la cohérence de l'objet Parent en mémoire)
        // Assurez-vous que la collection est initialisée (normalement via new HashSet<>() dans l'entité)
        student.setParent(parent);
        if (parent.getStudents()==null){
            parent.setStudents(new HashSet<>());
        }
        parent.getStudents().add(student);

        Parent savedParent = parentRepo.save(parent);
        return parentMapper.toDto(savedParent);
    }

    public List<Parent> findAll(){
        return this.parentRepo.findAll();
    }


}
