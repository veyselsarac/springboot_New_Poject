package com.tpe.springbootdemo181.domain;

import com.tpe.springbootdemo181.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole name;  // ROLE_ADMIN , ROLE_STUDENT


    @Override
    public String toString() {
        return "Role{" +
                ", name=" + name +
                '}';
    }


}