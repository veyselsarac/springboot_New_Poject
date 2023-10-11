package com.tpe.springbootdemo181.repository;

import com.tpe.springbootdemo181.domain.Role;
import com.tpe.springbootdemo181.domain.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(UserRole name);
}
