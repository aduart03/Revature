package com.test1.lecture.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Many students to one school, joined by school_id
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    // One student to many enrollments
    @OneToMany(
        mappedBy = "student", // (enrollments.student) -> student_id
        orphanRemoval = true,
        cascade = CascadeType.ALL
    )
    @JsonIgnore
    private List<Enrollment> enrollments = new ArrayList<>();

    // student firstname: not required, can be null
    @Size(max = 45)
    @Column(name = "first_name", length = 45)
    private String firstName;

    // student lastname: not requred, can be null
    @Size(max = 45)
    @Column(name = "last_name", length = 45)
    private String lastName;


    // student email: required
    @Email
    @NotBlank
    @Column(name = "email", length = 45, nullable = false, unique = true)
    private String email;

    public Student() {}

    public Student(String firstName, String lastName,@Email @NotBlank String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student(String firstName, String lastName, @Email @NotBlank String email, School school) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.school = school;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
        enrollment.setStudent(this);
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrollments.remove(enrollment);
        enrollment.setStudent(null);
    }
}