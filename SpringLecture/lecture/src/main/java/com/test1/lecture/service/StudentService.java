package com.test1.lecture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test1.lecture.dao.CourseRepository;
import com.test1.lecture.dao.EnrollmentRepository;
import com.test1.lecture.dao.SchoolRepository;
import com.test1.lecture.dao.StudentDAO;
import com.test1.lecture.dao.StudentRepository;
import com.test1.lecture.domain.School;
import com.test1.lecture.domain.Student;
import com.test1.lecture.dto.StudentWriteDto;
import com.test1.lecture.exceptions.RecordNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class StudentService {

    private final StudentRepository studentRepo;
    private final SchoolRepository schoolRepo;
    

    public StudentService(
        StudentRepository studentRepo,
        SchoolRepository schoolRepo) {

        this.studentRepo = studentRepo;
        this.schoolRepo = schoolRepo;
    }


    // STUDENTS
    @Transactional
    public Student insertStudent(StudentWriteDto dto) {
        School school  = schoolRepo.findById(dto.getSchoolId())
                            .orElse(null);
        
        Student newStudent = new Student(
            dto.getFirstName(),
            dto.getLastName(),
            dto.getEmail(),
            school
        );

        return studentRepo.save(newStudent);
    }

    @Transactional(readOnly = true)
    public List<Student> findStudentsBySchoolName(String name) {
        return studentRepo.findBySchool_Name(name);
    }

    @Transactional(readOnly = true)
    public List<Student> getAllStudents( int page, int count, boolean asc) {
        Sort.Direction direction = asc 
        ? Sort.Direction.ASC
        : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(
            page,
            count,
            Sort.by(direction, "id"));

            Page<Student> students = studentRepo.findAll(pageable);
        return students.getContent();
    }

    @Transactional(readOnly = true)
    public Page<Student> getStudentsPage(int page, int size) {
        return studentRepo.findAll(PageRequest.of(page, size, Sort.by("lastName")));
    }

    @Transactional(readOnly = true)
    public Student getStudentById(int id) {
        return studentRepo.findById(id).orElseThrow(
            () -> new RecordNotFoundException("Student not found with id: " + id)
        );
    }


    // Challenge 1: Part 2 - Service method
    @Transactional(readOnly = true)
    public List<Student> getStudentsByLastName(String lastName) {
        return studentRepo.findByLastName(lastName);
    }
    // Challenge 1 Continued : Part 2 - Service method
    @Transactional(readOnly = true)
    public Student getStudentByEmail(String email) {
        return studentRepo.findByEmail(email);
    }

    @Transactional
    public void deleteStudent(int id) {
        Student student = getStudentById(id);
        studentRepo.delete(student);
    }

    // Dirty checking: existingStudent is a managed entity, so changes
    // are saved automatically when the transaction commits.
    @Transactional
    public Student updateStudent(int id, StudentWriteDto dto) {
        Student existingStudent = getStudentById(id);
        existingStudent.setEmail(dto.getEmail());
        existingStudent.setFirstName(dto.getFirstName());
        existingStudent.setLastName(dto.getLastName());
        return existingStudent;
    }    
    
}