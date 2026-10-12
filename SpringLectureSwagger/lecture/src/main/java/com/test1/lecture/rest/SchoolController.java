package com.test1.lecture.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test1.lecture.domain.School;
import com.test1.lecture.service.SchoolService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<School> insertSchool(@Valid @RequestBody School school) {
        School savedSchool = schoolService.insertSchool(school);
        return ResponseEntity
            .created(
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedSchool.getId())
                    .toUri()
            )
            .body(savedSchool);
    }

    @GetMapping("/{id}")
    public School getSchoolById(@PathVariable int id) {
        return schoolService.getSchoolById(id);
    }
}