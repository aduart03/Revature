package com.test1.lecture.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test1.lecture.domain.School;

public interface SchoolRepository extends JpaRepository<School, Integer> {

}
