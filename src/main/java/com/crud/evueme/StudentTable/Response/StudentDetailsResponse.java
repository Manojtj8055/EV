package com.crud.evueme.StudentTable.Response;

import com.crud.evueme.StudentTable.DTO.StudentDTO;
import com.crud.evueme.StudentTable.Model.StudentDetails;
import lombok.Data;

import java.util.List;

@Data
public class StudentDetailsResponse {

    private StudentDTO studentDTO;

    private List<StudentDTO> data;

    private long totalPages;

    private long recordsTotal;

    private long currentRecords;

    private long recordsFiltered;

    private boolean success;

    private String error;

}
