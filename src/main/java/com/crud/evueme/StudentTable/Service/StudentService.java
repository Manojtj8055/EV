package com.crud.evueme.StudentTable.Service;

import com.crud.evueme.StudentTable.Model.StudentDetails;
import com.crud.evueme.StudentTable.Response.StudentDetailsResponse;

import java.util.List;
import java.util.Map;

public interface StudentService {

    StudentDetails createStudent(StudentDetails student);

    List<StudentDetails> getAllStudents();

    List<StudentDetails> getAllStudentsLive();

    StudentDetails getStudent(Long id);

    StudentDetails updateStudent(Long id, StudentDetails studentDetails);

    StudentDetails deleteStudent(Long id);

    StudentDetails getStudentAlive(Long id);

    public StudentDetailsResponse getPaginated(Map<String,String> formData);

    StudentDetailsResponse getSearchPaginated(Map<String, String> formData);
}