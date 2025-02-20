package com.crud.evueme.StudentMarks.DTO;

import com.crud.evueme.StudentMarks.Model.StudentMarksDetails;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentMarksDto {

    private Long id;
    private Long studentId;
    private String name;
    private Double subject1Marks;
    private Double subject2Marks;
    private Double subject3Marks;
    private Double totalMarks;
    private String createdBy;
    private LocalDateTime createdTime;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedTime;
    private Integer isDeleted;
    private String deletedBy;
    private LocalDateTime deletedTime;



    public StudentMarksDto(Long id, Long studentId, String name,
                           Double subject1Marks, Double subject2Marks, Double subject3Marks, Double totalMarks,
                           String createdBy, LocalDateTime createdTime,
                           String lastModifiedBy, LocalDateTime lastModifiedTime,
                           Integer isDeleted, String deletedBy, LocalDateTime deletedTime) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.subject1Marks = subject1Marks;
        this.subject2Marks = subject2Marks;
        this.subject3Marks = subject3Marks;
        this.totalMarks = totalMarks;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.isDeleted = isDeleted;
        this.deletedBy = deletedBy;
        this.deletedTime = deletedTime;
    }

    public StudentMarksDto(StudentMarksDetails studentMarks) {
        this.id = studentMarks.getId();
        this.studentId = studentMarks.getStudentId();
        this.subject1Marks = studentMarks.getSubject1Marks();
        this.subject2Marks = studentMarks.getSubject2Marks();
        this.subject3Marks = studentMarks.getSubject3Marks();
        this.totalMarks = studentMarks.getTotalMarks();
        this.createdBy = studentMarks.getCreatedBy();
        this.createdTime = studentMarks.getCreatedTime();
        this.lastModifiedBy = studentMarks.getLastModifiedBy();
        this.lastModifiedTime = studentMarks.getLastModifiedTime();
        this.isDeleted = studentMarks.getIsDeleted();
        this.deletedBy = studentMarks.getDeletedBy();
        this.deletedTime = studentMarks.getDeletedTime();

    }

}
