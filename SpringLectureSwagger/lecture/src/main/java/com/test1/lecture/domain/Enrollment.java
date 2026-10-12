package com.test1.lecture.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "enrollment")
public class Enrollment {
    /*
        Enrollment tables depends on :
        1. Student table
        2. Course Table
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "enrollment_date",
            nullable = false)
    private LocalDate enrollmentDate;

    @Column(name = "grade")
    private String grade;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id",
                nullable = false)
    private Student student;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(
        /*
            Enrollment holds the foreign key referencing the primary key of "Course"
            through the "course_id" column
         */
        name = "course_id", 
        nullable = false // course_id value cannot be null
    )
    private Course course;

    public Enrollment() {}

    public Enrollment(@NotNull Student student, @NotNull Course course, @NotNull LocalDate enrollmentDate, String grade) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}