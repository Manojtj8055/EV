package com.crud.evueme.StudentMarks.Service;

import com.crud.evueme.StudentMarks.DTO.StudentMarksDto;
import com.crud.evueme.StudentMarks.Model.StudentMarksDetails;
import com.crud.evueme.StudentMarks.Response.StudentMarksDetailsResponse;
import com.crud.evueme.StudentMarks.Respository.StudentMarksRepository;
import com.crud.evueme.StudentTable.Model.StudentDetails;
import com.crud.evueme.StudentTable.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentMarksServiceImpl implements StudentMarksService {

    @Autowired
    private StudentMarksRepository studentMarksRepository;


    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public StudentMarksDetails save(StudentMarksDetails studentMarks, Long studentId, String createdBy) {
        Optional<StudentDetails> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            studentMarks.setStudentId(studentId);
            studentMarks.calculateTotalMarks();
            studentMarks.setCreatedBy("User");
            studentMarks.setCreatedTime(LocalDateTime.now());
            return studentMarksRepository.save(studentMarks);
        } else {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }

    }

    public List<StudentMarksDetails> getAllStudentsWithMarks() {
        return studentMarksRepository.findAllActiveMarks();
    }

    public Optional<StudentMarksDetails> findStudentMarksByStudentId(Long studentId) {
        return studentMarksRepository.findById(studentId)
                .filter(sm -> sm.getIsDeleted() == 0);
    }

    public StudentMarksDetails updateMarks(StudentMarksDetails updatedStudentMarks, Long studentId, Long modifiedBy) {
        return studentMarksRepository.findById(studentId).map(existingMarks -> {
            existingMarks.setSubject1Marks(updatedStudentMarks.getSubject1Marks());
            existingMarks.setSubject2Marks(updatedStudentMarks.getSubject2Marks());
            existingMarks.setSubject3Marks(updatedStudentMarks.getSubject3Marks());
            existingMarks.calculateTotalMarks();
            existingMarks.updateAuditFields(modifiedBy);
            return studentMarksRepository.save(existingMarks);
        }).orElseThrow(() -> new RuntimeException("Marks Record not found with ID: " + studentId));
    }

    public void deleteMarks(Long studentId, Long deletedBy) {
        studentMarksRepository.findById(studentId).ifPresent(studentMarks -> {
            studentMarks.softDelete(deletedBy);
            studentMarksRepository.save(studentMarks);
        });
    }


    public List<StudentMarksDto> getStudentWithMarks() {
        return null;
    }

    @Override
    public Optional<StudentMarksDto> getStudentMarksById(Long id) {
        return studentMarksRepository.findData(id);
    }

    @Override
    public Optional<StudentMarksDto> getStudentPercentageById(Long id) {
        Optional<StudentMarksDto> studentMarks = studentMarksRepository.findData(id);
        studentMarks.ifPresent(studentMarksDto -> {
            double maxMarks = 300;
            double percentage = (studentMarksDto.getTotalMarks() != null && maxMarks > 0)
                    ? (studentMarksDto.getTotalMarks() / maxMarks) * 100 : 0.0;

            System.out.println("Calculated Percentage: " + percentage);
            studentMarksDto.setTotalMarks(percentage);
        });
        return studentMarks;
    }

    private List<StudentMarksDto> getStudentMarksDTO(List<StudentMarksDetails> studentMarksDetails) {
        List<StudentMarksDto> studentMarksDtos = new ArrayList<>();
        for (StudentMarksDetails studentMarks : studentMarksDetails) {
            studentMarksDtos.add(new StudentMarksDto(studentMarks));
        }
        return studentMarksDtos;
    }

    @Override
    public StudentMarksDetailsResponse getPaginatedStudentMarks(Map<String, String> formData) {
        StudentMarksDetailsResponse response = new StudentMarksDetailsResponse();
        try {
            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String sortField = formData.get("sort_field") == null ? "name" : formData.get("sort_field");
            String sortOrder = formData.get("sort_order") == null ? "asc" : formData.get("sort_order");
            Double searchText = formData.get("search_text") == null ? 0.0 : Double.valueOf(formData.get("search_text"));

            Sort sort = sortOrder.equalsIgnoreCase("asc") ?
                    Sort.by(Sort.Direction.ASC, sortField) :
                    Sort.by(Sort.Direction.DESC, sortField);

            Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
            Page<StudentMarksDto> page = studentMarksRepository.searchStudentMarks(searchText, pageable);

            response.setRecordsTotal(page.getTotalElements());
            response.setRecordsFiltered(page.getTotalElements());
            response.setTotalPages(page.getTotalPages());
            response.setData(page.getContent());
            response.setCurrentRecords(page.getContent().size());
            response.setSuccess(true);
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setError(ex.getMessage());
        }
        return response;
    }


}

