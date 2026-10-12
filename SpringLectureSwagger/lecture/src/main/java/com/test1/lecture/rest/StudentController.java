package com.test1.lecture.rest;

import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test1.lecture.domain.School;
import com.test1.lecture.domain.Student;
import com.test1.lecture.dto.StudentWriteDto;
import com.test1.lecture.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @Operation (summary = "Gets all students.", description = "Return every student from the database")
    public List<Student> getAllStudents(
        @RequestParam(defaultValue = "0") int  page,
         @RequestParam(defaultValue = "10") int count,
          @RequestParam(defaultValue = "true") boolean asc) {
        return studentService.getAllStudents(page, count, asc);
    }

    @GetMapping("/{id}")
    @Parameter(description = "Get student by the id." , required = true, example = "1")
    public Student getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    // Challenge 1: Rest endpoint that uses @Query
    // /api/students?lastName=Wilson
    @GetMapping(params = "lastName")
    public List<Student> getStudentsByLastName(@RequestParam String lastName) {
        return studentService.getStudentsByLastName(lastName);
    }
    // Challenge 1: continued
    // /api/students?email=something@email.com
    @GetMapping(params = "email")
    public Student getStudentByEmail(@RequestParam String email){
        return studentService.getStudentByEmail(email);
    }

    // /api/students?schoolName=Lincoln
    @GetMapping(params = "schoolName")
    public List<Student> getStudentsBySchoolName(@RequestParam String schoolName) {
        return studentService.findStudentsBySchoolName(schoolName);
    }
    
    @PostMapping
    public ResponseEntity<Student> insertStudent(@Valid @RequestBody StudentWriteDto dto) {
        Student saved = studentService.insertStudent(dto);
        return ResponseEntity
            .created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri())
            .body(saved);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody StudentWriteDto dto) {
        return studentService.updateStudent(id, dto);
    }

    // 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}