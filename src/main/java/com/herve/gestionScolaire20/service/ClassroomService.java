package com.herve.gestionScolaire20.service;

import com.herve.gestionScolaire20.models.Classroom;
import com.herve.gestionScolaire20.repository.ClassroomRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {
    private final ClassroomRepo classroomRepo;

    public ClassroomService(ClassroomRepo classroomRepo) {
        this.classroomRepo = classroomRepo;
    }

    public Classroom create(Classroom classroom){

        if (classroomRepo.existsByClassName(classroom.getClassName())){
            throw new IllegalStateException("The class name already exists");
        }
        return classroomRepo.save(classroom);
    }

    public List<Classroom> findAll(){
        return this.classroomRepo.findAll();
    }

    public void delete(Integer classroomId){
        classroomRepo.findById(classroomId)
                .orElseThrow(()->new EntityNotFoundException("classroom not found!!"));
        classroomRepo.deleteById(classroomId);
    }
}
