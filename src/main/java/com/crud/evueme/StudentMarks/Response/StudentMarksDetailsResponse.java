package com.crud.evueme.StudentMarks.Response;

import com.crud.evueme.StudentMarks.DTO.StudentMarksDto;
import lombok.Data;
import java.util.List;

@Data
public class StudentMarksDetailsResponse {
    private boolean success;
    private String error;
    private long recordsTotal;
    private long recordsFiltered;
    private int totalPages;
    private int currentRecords;
    private List<StudentMarksDto> data;
}
