package com.test1.lecture.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentWriteDto {

    @Size(max = 45)
    @JsonProperty("first_name")
    private String firstName;

    @Size(max = 45)
    @JsonProperty("last_name")
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @JsonProperty("school_id")
    private Integer schoolId;

    public StudentWriteDto() {}

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
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


}