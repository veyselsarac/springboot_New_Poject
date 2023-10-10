package com.tpe.springbootdemo181.repository;

import com.tpe.springbootdemo181.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String username);


}