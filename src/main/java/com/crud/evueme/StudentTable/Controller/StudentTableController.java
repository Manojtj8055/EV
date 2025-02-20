package com.crud.evueme.StudentTable.Controller;


import com.crud.evueme.StudentTable.Model.StudentDetails;
import com.crud.evueme.StudentTable.Repository.StudentRepository;
import com.crud.evueme.StudentTable.Response.StudentDetailsResponse;
import com.crud.evueme.StudentTable.Service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentTableController {

    @Autowired
    private StudentServiceImpl service;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("/saveId")
    public ResponseEntity<StudentDetails> createStudent(@RequestBody StudentDetails student) {
        return ResponseEntity.ok(service.createStudent(student));
    }

    @GetMapping("/list")
    public ResponseEntity<List<StudentDetails>> getAllStudents() {

        return ResponseEntity.ok(service.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDetails> getStudentById(@PathVariable Long id) {
        StudentDetails student = studentService.getStudent(id);
        // return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<StudentDetails> updateStudent(@PathVariable Long id, @RequestBody StudentDetails studentDetails) {
        StudentDetails updatedStudent = studentService.updateStudent(id, studentDetails);
        return updatedStudent != null ? ResponseEntity.ok(updatedStudent) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDetails> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/get")
    public List<StudentDetails> getAllStudentsList(){

        return studentService.getAllStudents();
    }

    @GetMapping("/alive")
    public ResponseEntity<List<StudentDetails>> getAllStudentsLive(){

      return ResponseEntity.ok(studentService.getAllStudentsLive())  ;
    }

    @GetMapping("singleAlive/{id}")
    public ResponseEntity<StudentDetails> getStudent(@PathVariable Long id){
        StudentDetails student = studentService.getStudentAlive(id);
        if(student == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(student);
    }

    @PostMapping("/get_paginated")
    public StudentDetailsResponse getPaginated(@RequestBody Map<String,String> formData){
    StudentDetailsResponse response = new StudentDetailsResponse();
    try{
    response  = service.getPaginated(formData);
    }catch (Exception e){
    response.setSuccess(false);
    response.setError(e.getMessage());
    }
    return response;
    }

    @PostMapping("/search_paginated")
    public StudentDetailsResponse getSearchPaginated(@RequestBody Map<String,String> formData){
        StudentDetailsResponse response = new StudentDetailsResponse();
        try{
            response  = service.getSearchPaginated(formData);
        }catch (Exception e){
            response.setSuccess(false);
            response.setError(e.getMessage());
        }
        return response;
    }
}