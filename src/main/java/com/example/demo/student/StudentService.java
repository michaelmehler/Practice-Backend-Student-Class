package com.example.demo.student;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired 
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
	}

    public void addNewStudent(Student student){
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.get_email());
        if (studentByEmail.isPresent())
            throw new IllegalStateException("email taken");
        studentRepository.save(student); 
    }

    public void deleteStudent(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException("student with id " + studentId + "does not exist");
        }
        studentRepository.deleteById(studentId);
    } 

    @Transactional //allows you to update database without queries
    public void updateStudent(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId).orElseThrow(
            () -> new IllegalStateException("student with id " + studentId + "does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(student.get_name(), name)){ 
            student.set_name(name); 
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.get_email(), email)){ 
            Optional<Student> studeOptional = studentRepository.findStudentByEmail(email);
            if (studeOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.set_email(email); 
        }
    }
  
}
