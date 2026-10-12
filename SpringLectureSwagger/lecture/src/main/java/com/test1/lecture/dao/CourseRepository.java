package com.test1.lecture.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test1.lecture.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
