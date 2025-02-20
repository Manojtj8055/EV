package com.crud.evueme.StudentTable.Model;

import com.crud.evueme.StudentMarks.Model.StudentMarksDetails;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "student")
public class StudentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(max=40)
    @Column(name = "name")
    private String name;

    @Length(max=40)
    @Column(name = "student_class")
    private String studentClass;

    @Length(max=40)
    @Column(name = "created_by")
    private String createdBy;
    private LocalDateTime createdTime;
    private Integer isDeleted;

    @Length(max=40)
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
    private LocalDateTime lastModifiedTime;

    @Length(max=40)
    @Column(name = "deleted_by")
    private String deletedBy;
    private LocalDateTime deletedTime;




}
