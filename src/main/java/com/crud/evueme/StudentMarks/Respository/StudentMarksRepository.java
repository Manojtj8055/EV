package com.crud.evueme.StudentMarks.Respository;

import com.crud.evueme.StudentMarks.DTO.StudentMarksDto;
import com.crud.evueme.StudentMarks.Model.StudentMarksDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentMarksRepository extends JpaRepository<StudentMarksDetails, Long> {

    @Query("SELECT sm FROM StudentMarksDetails sm WHERE sm.isDeleted = 0")
    List<StudentMarksDetails> findAllActiveMarks();


    @Query("SELECT new com.crud.evueme.StudentMarks.DTO.StudentMarksDto(" +
            "sm.id, " +
            "sm.studentId, " +
            "s.name, " +
            "sm.subject1Marks, " +
            "sm.subject2Marks, " +
            "sm.subject3Marks, " +
            "sm.totalMarks, " +
            "sm.createdBy, " +
            "sm.createdTime, " +
            "sm.lastModifiedBy, " +
            "sm.lastModifiedTime, " +
            "sm.isDeleted, " +
            "sm.deletedBy, " +
            "sm.deletedTime) " +
            "FROM StudentMarksDetails sm " +
            "LEFT JOIN StudentDetails s on s.id = sm.studentId and s.isDeleted = 0 " +
            "WHERE sm.id = :id")
    Optional<StudentMarksDto> findData(@Param("id") Long id);


    @Query("SELECT new com.crud.evueme.StudentMarks.DTO.StudentMarksDto(" +
            "sm.id, " +
            "sm.studentId, " +
            "s.name, " +
            "sm.subject1Marks, " +
            "sm.subject2Marks, " +
            "sm.subject3Marks, " +
            "sm.totalMarks, " +
            "sm.createdBy, " +
            "sm.createdTime, " +
            "sm.lastModifiedBy, " +
            "sm.lastModifiedTime, " +
            "sm.isDeleted, " +
            "sm.deletedBy, " +
            "sm.deletedTime) " +
            "FROM StudentMarksDetails sm " +
            "LEFT JOIN StudentDetails s ON s.id = sm.studentId AND s.isDeleted = 0 " +
            "WHERE sm.subject1Marks=:searchText or sm.subject2Marks=:searchText or sm.subject3Marks=:searchText or sm.totalMarks=:searchText")
    Page<StudentMarksDto> searchStudentMarks(@Param("searchText") Double searchText, Pageable pageable);



}

