package com.crud.evueme.StudentMarks.Model;

import com.crud.evueme.StudentTable.Model.StudentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "student_marks")
public class StudentMarksDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "student_id")
    private Long studentId;


    @Column(name="subject_1_marks")
    private Double subject1Marks;

    @Column(name="subject_2_marks")
    private Double subject2Marks;

    @Column(name="subject_3_marks")
    private Double subject3Marks;

    @Column(name="total_marks")
    private Double totalMarks;

    @Length(max=40)
    @Column(name="created_by")
    private String createdBy;

    private LocalDateTime createdTime;

    @Length(max=40)
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    private LocalDateTime lastModifiedTime;
    private Integer isDeleted;

    @Length(max=40)
    @Column(name = "deleted_by")
    private String deletedBy;
    private LocalDateTime deletedTime;

    public void calculateTotalMarks() {
        this.totalMarks = this.subject1Marks + this.subject2Marks + this.subject3Marks;
    }

    public void softDelete(Long userId) {
        this.isDeleted = 1;
        this.deletedBy = "User";
        this.deletedTime = LocalDateTime.now();
    }

    public void updateAuditFields(Long modifiedBy) {
        this.lastModifiedBy = "User";
        this.lastModifiedTime = LocalDateTime.now();
    }


}
