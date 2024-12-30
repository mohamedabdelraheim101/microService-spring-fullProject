package com.example.crud_task.Repositories;

import com.example.crud_task.Models.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
    boolean existsByEmail(String email);
    Optional<Student> findByEmail(String email);

    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);


}