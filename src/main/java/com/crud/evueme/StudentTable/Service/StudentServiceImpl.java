package com.crud.evueme.StudentTable.Service;

import com.crud.evueme.StudentTable.DTO.StudentDTO;
import com.crud.evueme.StudentTable.Model.StudentDetails;
import com.crud.evueme.StudentTable.Repository.StudentRepository;
import com.crud.evueme.StudentTable.Response.StudentDetailsResponse;
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

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;
    @Autowired
    private StudentRepository studentRepository;

    public StudentDetails createStudent(StudentDetails student) {
        student.setCreatedTime(LocalDateTime.now());
        student.setCreatedBy("User");
        student.setIsDeleted(0); // 0 means not deleted
        return repository.save(student);
    }


    public List<StudentDetails> getAllStudents() {

        return repository.findAll();
    }


    public List<StudentDetails> getAllStudentsLive() {
        return studentRepository.findByIsDeleted(0);
    }

    public StudentDetails getStudent(Long id) {

        return repository.findById(id).orElse(null);
    }


    public StudentDetails updateStudent(Long id, StudentDetails studentDetails) {
        StudentDetails student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setName(studentDetails.getName());
            student.setStudentClass(studentDetails.getStudentClass());
            student.setLastModifiedTime(LocalDateTime.now());
            student.setLastModifiedBy(studentDetails.getName());
            return studentRepository.save(student);
        }
        return null;
    }

    public StudentDetails deleteStudent(Long id) {
        StudentDetails student = studentRepository.findById(id).orElse(null);
        if (student == null) throw new AssertionError();
        student.setIsDeleted(1);
        student.setDeletedTime(LocalDateTime.now());
        student.setDeletedBy(student.getName());
        return studentRepository.save(student);
    }


    public StudentDetails getStudentAlive(Long id) {
        return studentRepository.findByIdAndIsDeleted(id, 0).orElse(null);
    }

    private List<StudentDTO> getStudentDTOS(List<StudentDetails> studentDetails) {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (StudentDetails studentdetails : studentDetails) {
            studentDTOS.add(new StudentDTO(studentdetails));
        }
        return studentDTOS;
    }

    @Override
    public StudentDetailsResponse getPaginated(Map<String, String> formData) {
//        logger.trace("Entering getPaginated method");

        StudentDetailsResponse response = new StudentDetailsResponse();
        try {
//            logger.trace("Received Data: {}", objectMapper.writeValueAsString(formData));

            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String sortField = formData.get("sort_field") == null ? "name" : formData.get("sort_field");
            String sortOrder = formData.get("sort_order") == null ? "asc" : formData.get("sort_order");


            Sort sort = sortOrder.equalsIgnoreCase("asc") ?
                    Sort.by(Sort.Direction.ASC, sortField) :
                    Sort.by(Sort.Direction.DESC, sortField);

            Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);


            Page<StudentDetails> page = studentRepository.findAllByIsDeleted(0, pageable);


            response.setRecordsTotal(page.getTotalElements());
            response.setRecordsFiltered(page.getTotalElements());
            response.setTotalPages(page.getTotalPages());
            response.setData(getStudentDTOS(page.getContent()));
            response.setCurrentRecords(response.getData().size());
            response.setSuccess(true);

        } catch (Exception ex) {
            response.setSuccess(false);
            response.setError(ex.getMessage());
        }
        return response;
    }

    @Override
    public StudentDetailsResponse getSearchPaginated(Map<String, String> formData) {
        StudentDetailsResponse response = new StudentDetailsResponse();
        try {
//            logger.trace("Received Data: {}", objectMapper.writeValueAsString(formData));

            int pageNumber = formData.get("current_page") == null ? 0 : Integer.parseInt(formData.get("current_page"));
            int pageSize = formData.get("page_size") == null ? 10 : Integer.parseInt(formData.get("page_size"));
            String searchText = formData.get("search_text") == null ? null : String.valueOf(formData.get("search_text"));
            String sortField = formData.get("sort_field") == null ? "name" : formData.get("sort_field");
            String sortOrder = formData.get("sort_order") == null ? "asc" : formData.get("sort_order");


            Sort sort = sortOrder.equalsIgnoreCase("asc") ?
                    Sort.by(Sort.Direction.ASC, sortField) :
                    Sort.by(Sort.Direction.DESC, sortField);

            Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);


            Page<StudentDetails> page = studentRepository.searchStudents(searchText, 0, pageable);


            response.setRecordsTotal(page.getTotalElements());
            response.setRecordsFiltered(page.getTotalElements());
            response.setTotalPages(page.getTotalPages());
            response.setData(getStudentDTOS(page.getContent()));
            response.setCurrentRecords(response.getData().size());
            response.setSuccess(true);

        } catch (Exception ex) {
            response.setSuccess(false);
            response.setError(ex.getMessage());
        }
        return response;
    }


}
