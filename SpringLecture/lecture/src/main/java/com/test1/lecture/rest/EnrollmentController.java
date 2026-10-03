package com.test1.lecture.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test1.lecture.domain.Enrollment;
import com.test1.lecture.dto.EnrollmentWriteDto;
import com.test1.lecture.service.EnrollmentService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/enrollments")
public class EnrollmentController {
    EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService){
        this.enrollmentService = enrollmentService;
    }

    // Insert Enrollment to Enrollment list of specific student
    @PostMapping 
    public ResponseEntity<Enrollment> insertEnrollment(@Valid @RequestBody EnrollmentWriteDto enrDto){
        Enrollment saved = enrollmentService.insertEnrollment(enrDto);

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saved.getId())
                    .toUri())
                .body(saved);
    }

}
