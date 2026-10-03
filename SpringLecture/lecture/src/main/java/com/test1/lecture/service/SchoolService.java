package com.test1.lecture.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test1.lecture.dao.SchoolRepository;
import com.test1.lecture.domain.School;
import com.test1.lecture.exceptions.RecordNotFoundException;

@Service 
public class SchoolService {

    private final SchoolRepository schoolRepo;

    public SchoolService(SchoolRepository schoolRepo){
        this.schoolRepo = schoolRepo;
    }

    // SCHOOLS
    @Transactional(readOnly = true)
    public School getSchoolById(int id) {
        return schoolRepo.findById(id).orElseThrow(
            () -> new RecordNotFoundException("School not found with id: " + id)
        );
    }
    
    @Transactional
    public School insertSchool(School school) {
        return schoolRepo.save(school);
    }

}
