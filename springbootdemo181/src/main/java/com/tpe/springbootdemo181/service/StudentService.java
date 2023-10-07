package com.tpe.springbootdemo181.service;

import com.tpe.springbootdemo181.domain.Student;
import com.tpe.springbootdemo181.dto.StudentDTO;

import com.tpe.springbootdemo181.exception.ConflictException;
import com.tpe.springbootdemo181.exception.ResourceNotFoundException;
import com.tpe.springbootdemo181.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void updateStudent(Long id, StudentDTO studentDTO) {

        // Find the existing Student
        Student student = getStudentById(id);

        // If this email already belongs to another Student
        boolean emailExists = studentRepository.existsByEmail(studentDTO.getEmail());

        if (emailExists && !student.getEmail().equals(studentDTO.getEmail())){
            throw new ConflictException("Student with e-mail: "+studentDTO.getEmail()+" already exists.");
        }

        // Map StudentDTO fields to the real Student now
        student.setName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setGrade(studentDTO.getGrade());
        student.setEmail(studentDTO.getEmail());
        student.setPhoneNumber(studentDTO.getPhoneNumber());

        // Save the updated student object
        // .saveOrUpdate
        studentRepository.save(student);

    }

    public Page<Student> getAllStudentsWithPagination(Pageable pageable) {

        return studentRepository.findAll(pageable);

    }

    public List<Student> findStudentsByLastName(String lastName) {

    return studentRepository.findByLastName(lastName);
    }

    public List<Student> findStudentByGrade(int grade) {

        return studentRepository.findByGradeWithJPQL(grade);
    }

    public StudentDTO getStudentDTOById(Long id) {

        // First Way:
    /*
    Student student = getStudentById(id);
    StudentDTO studentDTO = new StudentDTO();
    studentDTO.setFirstName(student.getName());
    studentDTO.setLastName(student.getLastName());
    studentDTO.setGrade(student.getGrade());
    studentDTO.setPhoneNumber(student.getPhoneNumber());
    studentDTO.setEmail(student.getEmail());
    studentDTO.setId(0L);
     */

        // Second Way:
        // Student student = getStudentById(id);
        //StudentDTO studentDTO = new StudentDTO(student);

        // Third Way:
        StudentDTO studentDTO = studentRepository.findStudentDTOById(id).orElseThrow(()-> new ResourceNotFoundException("Couldn't find the student with id: "+id));

        return studentDTO;


    }
}
