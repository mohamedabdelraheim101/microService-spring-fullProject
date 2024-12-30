package com.example.crud_task.Models.dtoMappers;

import com.example.crud_task.Models.DTO.DepartmentDto;
import com.example.crud_task.Models.Entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public DepartmentDto toDto(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }

    public Department toEntity(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        return department;
    }
}
