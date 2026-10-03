package com.test1.lecture.dao;

import com.test1.lecture.domain.Student;
import java.util.List;

public interface StudentDAO {

    // POST: Insert a student
    Student insert(Student student);
    //List<Student> 

    // GET: Find all students
    List<Student> findAll();

    // Get: find by id
    Student findById(int id);

    // Get: find by Last Name
    List<Student> findByLastName(String lastName);

    // Delete student
    void delete(Student student);

}
