package com.crud.evueme.StudentMarks.Service;

import com.crud.evueme.StudentMarks.DTO.StudentMarksDto;
import com.crud.evueme.StudentMarks.Model.StudentMarksDetails;
import com.crud.evueme.StudentMarks.Response.StudentMarksDetailsResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentMarksService {
    StudentMarksDetails save(StudentMarksDetails studentMarks, Long studentId, String createdBy);

    List<StudentMarksDetails> getAllStudentsWithMarks();

    Optional<StudentMarksDetails> findStudentMarksByStudentId(Long studentId);

    StudentMarksDetails updateMarks(StudentMarksDetails updatedStudentMarks, Long studentId, Long modifiedBy);

    void deleteMarks(Long studentId, Long deletedBy);

    List<StudentMarksDto> getStudentWithMarks();

    Optional<StudentMarksDto> getStudentMarksById(Long id);

    Optional<StudentMarksDto> getStudentPercentageById(Long id);

    StudentMarksDetailsResponse getPaginatedStudentMarks(Map<String,String> formData);
}
