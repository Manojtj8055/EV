package com.crud.evueme.StudentMarks.Controller;


import com.crud.evueme.StudentMarks.DTO.StudentMarksDto;
import com.crud.evueme.StudentMarks.Model.StudentMarksDetails;
import com.crud.evueme.StudentMarks.Response.StudentMarksDetailsResponse;
import com.crud.evueme.StudentMarks.Service.StudentMarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/studentMarks")
public class StudentMarksController {



    @Autowired
    private StudentMarksService studentMarksService;

    @PostMapping("/save/{studentId}")
    public StudentMarksDetails saveMarks(@PathVariable Long studentId,
                                         @RequestBody StudentMarksDetails studentMarks,
                                         @RequestParam Long createdBy) {
        return studentMarksService.save(studentMarks, studentId, "User");
    }

    @GetMapping("/all")
    public List<StudentMarksDetails> getAllStudentMarks() {
        return studentMarksService.getAllStudentsWithMarks();
    }

//    @GetMapping("/{id}")
//    public Optional<StudentMarksDetails> getStudentMarksById(@PathVariable Long id) {
//        return studentMarksService.findStudentMarksByStudentId(id);
//    }

    @PutMapping("/update/{studentId}")
    public StudentMarksDetails updateMarks(@PathVariable Long studentId, @RequestBody StudentMarksDetails updatedStudentMarks,
                                           @RequestParam Long modifiedBy) {
        return studentMarksService.updateMarks(updatedStudentMarks, studentId, modifiedBy);
    }

    @DeleteMapping("/delete/{studentId}")
    public String deleteStudentMarks(@PathVariable Long studentId, @RequestParam Long deletedBy) {
        studentMarksService.deleteMarks(studentId, deletedBy);
        return "Marks soft deleted successfully";
    }


    @GetMapping("/name/{id}")
    public ResponseEntity<?> getStudentMarksByStudentId(@PathVariable Long id) {
    Optional<StudentMarksDto>  studentMarks   = studentMarksService.getStudentMarksById(id);

    return studentMarks.map(ResponseEntity::ok)
            .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("/per/{id}")
    public ResponseEntity<?> getStudentPercentageByStudentId(@PathVariable Long id) {
        Optional<StudentMarksDto>  studentMarks   = studentMarksService.getStudentPercentageById(id);

        return studentMarks.map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/search")
    public StudentMarksDetailsResponse searchMarksPaginated(@RequestBody Map<String, String> formData) {
        StudentMarksDetailsResponse response = new StudentMarksDetailsResponse();
        try{
            response = studentMarksService.getPaginatedStudentMarks(formData);
        }catch (Exception e){
            response.setSuccess(false);
            response.setError(e.getMessage());
        }
        return response;
    }

    }

