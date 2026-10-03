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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity 
@Table (name = "course")
public class Course {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 20)
    @NotBlank 
    @Column (name ="name", length = 20)
    private String name;

    /*
        One Course to many enrollments:
        - mapped by "course" : (Enrollment.course) maps/links Course and enrollments entities.
        Course is parent, Enrollment is child. this is a 2 way setup, you'll have to link them
        with a @JoinColumn in Enrollment.java.

        - orphanRemoval = true : If you remove an enrollment from this list, Hibernate
        deletes that enrollment row.

        -cascade = Cascade.ALL :(Remember ON DELETE/UPDATE/ CASCADE) 
        Whatever you do to course, Hibernate does to enrollments. 
        Save the course and its new enrollments get saved too; delete the course and its 
        enrollments get deleted too.
      */
    @OneToMany (
        mappedBy = "course",
        orphanRemoval = true, //
        cascade = CascadeType.ALL
    )
    @JsonIgnore 
    private List<Enrollment> enrollments = new ArrayList<>();

    public Course(){}

    public Course( String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    // public void setId(Integer id) {
    //     this.id = id;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void addEnrollment(Enrollment enrollment){
        enrollments.add(enrollment); // add enrollments to enrollment list
        enrollment.setCourse(this); // synchronize result with this specific enrollment for this course
    }

    public void removeEnrollment(Enrollment enrollment){
        enrollments.remove(enrollment); // remove enrollembt from enrollments list
        enrollment.setCourse(null); // synchronize or update on the course

    }


}
