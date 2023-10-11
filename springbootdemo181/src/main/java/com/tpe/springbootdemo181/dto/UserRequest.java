package com.tpe.springbootdemo181.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//User DTO
public class UserRequest {

    @NotBlank(message = "Provide a valid firstName")
    private String firstName;

    @NotBlank(message = "Provide a valid lastName")
    private String lastName;

    @NotBlank(message = "Provide a valid userName")
    @Size(min = 5,max = 10, message = "Provide a valid username, length between 5-10")
    private String userName;

    @NotBlank(message = "Provide a valid password")
    private String password;



}
