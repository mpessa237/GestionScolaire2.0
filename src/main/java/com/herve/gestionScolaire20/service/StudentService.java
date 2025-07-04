package com.herve.gestionScolaire20.service;

import com.herve.gestionScolaire20.dto.StudentReqDTO;
import com.herve.gestionScolaire20.dto.StudentRespDTO;
import com.herve.gestionScolaire20.mapper.StudentMapper;
import com.herve.gestionScolaire20.models.Classroom;
import com.herve.gestionScolaire20.models.Student;
import com.herve.gestionScolaire20.repository.ClassroomRepo;
import com.herve.gestionScolaire20.repository.StudentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    private final ClassroomRepo classroomRepo;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepo studentRepo, ClassroomRepo classroomRepo, StudentMapper studentMapper) {
        this.studentRepo = studentRepo;
        this.classroomRepo = classroomRepo;
        this.studentMapper = studentMapper;
    }

    public StudentRespDTO create(StudentReqDTO studentReqDTO){

        Classroom classroom = classroomRepo.findById(studentReqDTO.getClassroomId())
                .orElseThrow(()->new EntityNotFoundException("classroom not found!"));

        if (studentRepo.existsByEmail(studentReqDTO.getEmail())){
            throw new IllegalStateException("email already exists");
        }

        Student student = studentMapper.toEntity(studentReqDTO);
        student.setClassroom(classroom);

        String registrationNumber = generateRegistrationNumber();
        student.setRegistrationNumber(registrationNumber);

        Student savedStudent = studentRepo.save(student);
        return studentMapper.toDto(savedStudent);

    }

    public String generateRegistrationNumber(){
        String dateDepart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long countToday = studentRepo.countByCreatedDate(LocalDate.now());
        String numberPart = String.format("%03d",countToday + 1);
        return "MAT" + dateDepart + numberPart;
    }

    public List<Student> findAll(){
        return this.studentRepo.findAll();
    }

    public Student update(Student student,Integer studentId){
        Optional<Student> studentOptional = studentRepo.findById(studentId);

        if (studentOptional.isEmpty())
            throw new EntityNotFoundException("student not found!");
        if (student.getLastname()!=null)
            studentOptional.get().setLastname(student.getLastname());
        if (student.getFirstname()!=null)
            studentOptional.get().setFirstname(student.getFirstname());
        if (student.getDateOfBirth()!=null)
            studentOptional.get().setDateOfBirth(student.getDateOfBirth());
        if (student.getEmail()!=null)
            studentOptional.get().setEmail(student.getEmail());

        return this.studentRepo.saveAndFlush(studentOptional.get());
    }

}
