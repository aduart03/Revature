package com.test1.lecture.dao;

import com.test1.lecture.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Challenge: please create a endpoint, service method,
    // and repo implementation that ultimately
    // uses @Query to return results to the client. I don't care what it does
    // 
    // Part 1: Repo implementation using @Query
    // 
    // Query : JPQL
    /*
        why is this not "first_name" etc? is it because it matches with the java fields instead of the json properties?

        Answer:
        Yes, it matches the Java field names
        JPQL queries your entities, not tables,
        so you write Student (the class) and s.lastName (the field).
        Hibernate then translates that into real SQL using your @Column names (last_name).

        Remember: Student has the fields in camel case : firstName

    */
    @Query (
        """
        Select s
        FROM Student s
        WHERE s.lastName = :lastName
        ORDER BY s.id ASC
        """
    )
    List<Student> findByLastName(@Param("lastName") String lastName);
    List<Student> findBySchool_Name(String name);

    // Challenge 1 Continued: email is unique, careful with that
    @Query("""
        Select s
        FROM Student s
        WHERE s.email = :email
            """)
    Student findByEmail(@Param("email") String email);
}