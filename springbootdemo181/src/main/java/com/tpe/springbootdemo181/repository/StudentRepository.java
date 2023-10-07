package com.tpe.springbootdemo181.repository;

import com.tpe.springbootdemo181.domain.Student;
import com.tpe.springbootdemo181.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // optional since we have extended JpaRepository already.
public interface StudentRepository extends JpaRepository<Student, Long> {  // Pojo class, primary key data type.
    boolean existsByEmail(String email);

    List<Student> findByLastName(String lastName);


    List<Student> findByGrade(int grade);
    // Find a Student By Grade With SQL
    // ===========
    // pGrade is just a variable.
    @Query(value = "SELECT * FROM student s WHERE s.grade =:pGrade", nativeQuery = true)
    List<Student> findByGradeWithSQL(@Param("pGrade") int grade);

    // Find a Student By Grade With JPQL (Java Persistence Query Language)
    @Query("SELECT s FROM Student s WHERE s.grade =:pGrade")
    List<Student> findByGradeWithJPQL(@Param("pGrade") int grade);


    // new StudentDTO(student)
    @Query("SELECT new com.tpe.springbootdemo181.dto.StudentDTO(s) FROM Student s WHERE s.id=:id")
    Optional<StudentDTO>  findStudentDTOById(@Param("id") Long id);
}
