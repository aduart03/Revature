package com.test1.lecture.rest;

import org.hibernate.annotations.Mutability;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.*;
import com.test1.lecture.domain.Student;
import com.test1.lecture.dto.StudentWriteDto;
import com.test1.lecture.service.StudentService;


@Controller 
public class StudentGraphQLController {
    private final StudentService studentService;

    StudentGraphQLController(StudentService studentService){
        this.studentService = studentService;
    }

    @QueryMapping 
    public List<Student> getAllStudents(){
        return studentService.getAllStudents(0, 50, true);
    }
    @QueryMapping 
    public Student getStudentById(@Argument int id){
        return studentService.getStudentById(id);
    }
    @MutationMapping 
    public Student createStudent (
        @Argument String firstName,
        @Argument String lastName,
        @Argument String email,
        @Argument Integer schoolId
    ){
        StudentWriteDto dto = new StudentWriteDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setSchoolId(schoolId);
        return studentService.insertStudent(dto);
    }

}
