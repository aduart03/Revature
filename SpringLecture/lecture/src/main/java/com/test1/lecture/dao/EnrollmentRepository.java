package com.test1.lecture.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test1.lecture.domain.*;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    //Enrollment addEnrollment(Enrollment enrollment);
}
