package com.tpe.springbootdemo181.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Setter
@NoArgsConstructor
public class Book {

    /*

            Pojo Class:

                    private fields / variables
                    Default / Parameterized Constructors
                    Getters & Setters
                    toString (Optional)

     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("bookName")
    private String name;

    @ManyToOne
    @JoinColumn(name = "stu_id")
    @JsonIgnore() // Ignoring the field in JSON data. So that we won't have infinite loop problem.
    private Student student;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public Student getStudent() {
        return student;
    }

}