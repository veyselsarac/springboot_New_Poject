package com.tpe.springbootdemo181.service;

import com.tpe.springbootdemo181.domain.Student;
import com.tpe.springbootdemo181.exception.ConflictException;
import com.tpe.springbootdemo181.exception.ResourceNotFoundException;
import com.tpe.springbootdemo181.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAllStudent(){

        List<Student> students = studentRepository.findAll();

    return students;

    }


    public void saveStudent(Student student) {

        if (studentRepository.existsByEmail(student.getEmail())){
            throw new ConflictException("Student with e-mail: " + student.getEmail()+ " already exists.");
        }

        studentRepository.save(student);

    }

    // .findById() will return an optional

    public Student getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Couldn't find the Student With ID: " + id));
        return student;
    }


    public void deleteStudent(Long id) {
        // studentRepository.deleteById(id);

        // First we need to check whether if we have the student or not
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}
