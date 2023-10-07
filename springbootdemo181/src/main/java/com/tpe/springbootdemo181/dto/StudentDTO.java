package com.tpe.springbootdemo181.dto;


import com.tpe.springbootdemo181.domain.Student;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

// Student Data Transfer Object
public class StudentDTO {

    private Long id;

    @NotBlank(message = "Name cannot be empty or null.")
    @NotEmpty(message = "Name cannot be empty.")
    @NotNull(message = "Name cannot be null.")
    @Size(min = 2, max = 25, message = "Name should be between {min} and {max}.")
    private String firstName;

    private String lastName;

    private Integer grade;

    @Email(message = "Provide a valid mail.") // @, .com
    private String email;

    private String phoneNumber;

    private LocalDateTime creationDate = LocalDateTime.now();


    // Constructor
    public StudentDTO() {

    }

    public StudentDTO(Student student){

        this.setId(0L);
        this.setFirstName(student.getName());
        this.setLastName(student.getLastName());
        this.setGrade(student.getGrade());
        this.setEmail(student.getEmail());
        this.setPhoneNumber(student.getPhoneNumber());

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }


}