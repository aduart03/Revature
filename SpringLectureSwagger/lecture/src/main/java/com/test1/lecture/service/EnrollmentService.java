package com.test1.lecture.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test1.lecture.dao.CourseRepository;
import com.test1.lecture.dao.EnrollmentRepository;
import com.test1.lecture.dao.StudentRepository;
import com.test1.lecture.domain.Course;
import com.test1.lecture.domain.Enrollment;
import com.test1.lecture.domain.Student;
import com.test1.lecture.dto.EnrollmentWriteDto;
import com.test1.lecture.dto.StudentWriteDto;

@Service
public class EnrollmentService {

    
    private StudentRepository studentRepo;
    private EnrollmentRepository enrollmentRepo;
    private CourseRepository courseRepo;
    //private EnrollmentWriteDto enrDto;


    // Contructor injection
    public EnrollmentService(
        EnrollmentRepository enrollmentRepo,
        //EnrollmentWriteDto enrDto,
        StudentRepository studentRepo,
        CourseRepository courseRepo
        ){
        this.enrollmentRepo = enrollmentRepo;
        //this.enrDto = enrDto;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }


    // To add enrollment, you must know what student to add it to
    // and what course to refer to
    // Constructor for enrolment:
    /*
    public Enrollment(Student student, Course course, LocalDate enrollmentDate, String grade) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.grade = grade;
    }
    */
   @Transactional 
    public Enrollment insertEnrollment(EnrollmentWriteDto enrDto ){
        Student student = studentRepo.findById(enrDto.getStudentId())
                            .orElse(null);
        Course course = courseRepo.findById(enrDto.getCourseId())
                        .orElse(null);

        Enrollment newEnrollment = new Enrollment(
            student,
            course,
            enrDto.getEnrollmentDate(),
            enrDto.getGrade()
        );
        return enrollmentRepo.save(newEnrollment);
    }

}
