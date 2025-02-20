package com.crud.evueme.StudentTable.DTO;

import com.crud.evueme.StudentTable.Model.StudentDetails;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class StudentDTO {

    private Long id;
    private String name;
    private String studentClass;
    private String createdBy;
    private LocalDateTime createdTime;
    private Integer isDeleted;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedTime;
    private String deletedBy;
    private LocalDateTime deletedTime;

    public StudentDTO(Long id, String name, String studentClass, String createdBy,
                      LocalDateTime createdTime, Integer isDeleted, String lastModifiedBy,
                      LocalDateTime lastModifiedTime, String deletedBy, LocalDateTime deletedTime) {
        this.id = id;
        this.name = name;
        this.studentClass = studentClass;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.isDeleted = isDeleted;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.deletedBy = deletedBy;
        this.deletedTime = deletedTime;

    }

    public StudentDTO(StudentDetails studentDetails) {
        this.id = studentDetails.getId();
        this.name = studentDetails.getName();
        this.studentClass = studentDetails.getStudentClass();
        this.createdBy = studentDetails.getCreatedBy();
        this.createdTime = studentDetails.getCreatedTime();
        this.isDeleted = studentDetails.getIsDeleted();
        this.lastModifiedBy = studentDetails.getLastModifiedBy();
        this.lastModifiedTime = studentDetails.getLastModifiedTime();
        this.deletedBy = studentDetails.getDeletedBy();
        this.deletedTime = studentDetails.getDeletedTime();

    }
}

