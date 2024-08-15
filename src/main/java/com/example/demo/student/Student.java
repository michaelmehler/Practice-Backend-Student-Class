package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
        name="student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence" 
    )

    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    @Transient //Says age does not need to be a column in the database and instead will just be calculated
    private Integer age;

    public Student(){
    }

    public Student(Long id, String name, String email, LocalDate dob){
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(String name, String email, LocalDate dob){
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Long get_id(){
        return this.id; 
    }

    public String get_name(){
        return this.name; 
    }

    public String get_email(){
        return this.email; 
    }

    public LocalDate get_dob(){
        return this.dob; 
    }

    public Integer get_age(){
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void set_id(Long id){
        this.id = id;
    }

    public void set_name(String name){
        this.name = name;
    }

    public void set_email(String email){
        this.email = email;
    }

    public void set_dob(LocalDate dob){
        this.dob = dob;
    }

    @Override
    public String toString(){
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + 
        ", dob=" + dob + ", age=" + age + "}";
    }
}
