package com.tpe.springbootdemo181.repository;

import com.tpe.springbootdemo181.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // optional since we have extended JpaRepository already.
public interface StudentRepository extends JpaRepository<Student, Long> { // Pojo class, primary key data type.





}
