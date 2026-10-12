package com.test1.lecture.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class EnrollmentWriteDto {

    // Takes in
    @NotNull 
    @JsonProperty("student_id")
    private Integer studentId;

    @NotNull 
    @JsonProperty("course_id")
    private Integer courseId;

    @NotNull 
    @JsonProperty("enrollment_date")
    private LocalDate enrollmentDate;

    @JsonProperty("grade")
    private String grade;

    public EnrollmentWriteDto(){}

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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


}
