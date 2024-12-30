package com.example.crud_task.Repositories;

import com.example.crud_task.Models.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> , JpaSpecificationExecutor<Department> {
    boolean existsByName(String name);

}
