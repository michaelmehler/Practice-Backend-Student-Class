package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean 
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mike = new Student(
                1L,
                "mike", 
                "mike1@gmail.com", 
                LocalDate.of(2005, Month.FEBRUARY, 19)
                ); 
            
            Student bob = new Student(
                    "bob", 
                    "bob1@gmail.com", 
                    LocalDate.of(2005, Month.FEBRUARY, 20) 
                    ); 

            repository.saveAll(List.of(mike, bob)); 
        };
    }
}
