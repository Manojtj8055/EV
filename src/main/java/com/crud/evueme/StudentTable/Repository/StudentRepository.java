package com.crud.evueme.StudentTable.Repository;

import com.crud.evueme.StudentTable.DTO.StudentDTO;
import com.crud.evueme.StudentTable.Model.StudentDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentDetails, Long> {

    List<StudentDetails> findByIsDeleted(Integer isDeleted);

    Optional<StudentDetails> findByIdAndIsDeleted(Long id, Integer isDeleted);


    Page<StudentDetails> findAllByIsDeleted(Integer isDeleted, Pageable pageable);

    @Query("SELECT s FROM StudentDetails s WHERE (:searchText IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :searchText, '%'))) AND s.isDeleted = :isDeleted")
    Page<StudentDetails> searchStudents(@Param("searchText") String searchText,
                                        @Param("isDeleted") int isDeleted,
                                        Pageable pageable);
}
