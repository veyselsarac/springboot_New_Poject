package com.tpe.springbootdemo181.controller;

import com.tpe.springbootdemo181.domain.Student;
import com.tpe.springbootdemo181.dto.StudentDTO;

import com.tpe.springbootdemo181.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // To create rest QPI and tell Spring that we are using JSON bodies
@RequestMapping("/students") // http://localhost:8080/students
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Get all students
    @GetMapping     // http://localhost:8080/students + GET
        public ResponseEntity<List<Student>> getAllStudents(){

            List<Student> students = studentService.findAllStudent();

            return ResponseEntity.ok(students);

        }

        // Save a student
        @PostMapping  // http://localhost:8080/students + POST
        public ResponseEntity<Map<String, String>> createStudent(@Valid @RequestBody Student student) {
        /*
                message: Student has been created successfully
                status : ture
        */
            studentService.saveStudent(student);

            Map<String, String> map = new HashMap<>();
            map.put("message" , "Student has been created successfully");
            map.put("status", "true");

            return new ResponseEntity<>(map, HttpStatus.CREATED);
        }

        // Get A Student By Id
        @GetMapping("/query")  // http://localhost:8080/students/query?id=2 + GET
        public ResponseEntity<Student> getStudentByIdRequestParam(@RequestParam("id") Long id){
        Student student = studentService.getStudentById(id);

        return new ResponseEntity<>(student, HttpStatus.OK);

        }


        // Get A Student By Id
        @GetMapping("/{id}")    // http://localhost:8080/students
        public ResponseEntity<Student> getStudentByIdPathVariable(@PathVariable Long id){

        Student student = studentService.getStudentById(id);

        return ResponseEntity.ok(student); // 200

        }

        // Delete Student By Id
        @DeleteMapping("/{id}")  // http://localhost:8080/students
        public ResponseEntity<Map<String, String>> deleteStudentBuId(@PathVariable Long id){

        studentService.deleteStudent(id);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Student has been deleted successfully");
        map.put("status", "true");

        return ResponseEntity.ok(map); //  200

        }

    // Update a Student By id
    @PutMapping("/{id}") // http://localhost:8080/students/2 + PUT
    public ResponseEntity<Map<String, String>> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO){

        studentService.updateStudent(id, studentDTO);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Student has been updated successfully.");
        map.put("status", "true");

        return ResponseEntity.ok(map);

    }


    // Getting students with pagination
    @GetMapping("/pagination") // http://localhost:8080/students/pagination?=page=0&size=10&sort=name&direction=ASC + get
    public ResponseEntity<Page<Student>> getAllStudentsWithPagination(
            @RequestParam("page") int page, // page number
            @RequestParam("size") int size, // items per page
            @RequestParam("sort") String sort, // Sort based on a data (name, price, etc.)  (optional)
            @RequestParam("direction") Sort.Direction direction // Ascending or descending order
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));

        Page<Student> pageOfStudents = studentService.getAllStudentsWithPagination(pageable);

        return ResponseEntity.ok(pageOfStudents);
    }


}
