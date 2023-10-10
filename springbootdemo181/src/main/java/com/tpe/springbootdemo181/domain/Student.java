package com.tpe.springbootdemo181.domain;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
//@RequiredArgsConstructor
@AllArgsConstructor // Parameterized Constructor
@NoArgsConstructor // Default Constructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Name cannot be empty or null.")
    @NotEmpty(message = "Name cannot be empty.")
    @NotNull(message = "Name cannot be null.")
    @Size(min = 2, max = 25, message = "Name should be between {min} and {max}.")
    @Column(nullable = false, length = 25)
    private String name;

    @Column(nullable = false, length = 25)
    private String lastName;


    private Integer grade;

    @Column(nullable = false, length = 50, unique = true)
    @Email(message = "Provide a valid mail.") // @, .com
    private String email;

    private String phoneNumber;

    @Setter(AccessLevel.NONE)
    private LocalDateTime creationDate = LocalDateTime.now();

    @OneToMany(mappedBy = "student")
    private List<Book> books = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "usr_id")
    private User user;

}