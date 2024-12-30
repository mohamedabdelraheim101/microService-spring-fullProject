package com.example.crud_task.Services;

import com.example.crud_task.Models.DTO.DepartmentDto;
import com.example.crud_task.Models.Entity.Department;
import com.example.crud_task.Models.dtoMappers.DepartmentMapper;
import com.example.crud_task.Repositories.DepartmentRepo;
import com.example.crud_task.Specifications.DepartmentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private DepartmentMapper departmentMapper;

    public Page<DepartmentDto> getAllDepartments(String name, Pageable pageable) {
        Page<Department> departments = departmentRepo.findAll(
                DepartmentSpecification.filterDepartments(name), pageable
        );
        return departments.map(departmentMapper::toDto);
    }

    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        if (departmentRepo.existsByName(departmentDto.getName())) {
            throw new IllegalArgumentException("Department with this name already exists.");
        }
        Department department = departmentMapper.toEntity(departmentDto);
        Department savedDepartment = departmentRepo.save(department);
        return departmentMapper.toDto(savedDepartment);
    }

    public DepartmentDto getDepartmentById(int id) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found."));
        return departmentMapper.toDto(department);
    }

    public DepartmentDto updateDepartment(int id, DepartmentDto updatedDepartmentDto) {
        Department existingDepartment = departmentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found."));

        existingDepartment.setName(updatedDepartmentDto.getName());
        existingDepartment.setDescription(updatedDepartmentDto.getDescription());

        Department savedDepartment = departmentRepo.save(existingDepartment);
        return departmentMapper.toDto(savedDepartment);
    }

    public void deleteDepartment(int id) {
        if (!departmentRepo.existsById(id)) {
            throw new IllegalArgumentException("Department not found.");
        }
        departmentRepo.deleteById(id);
    }
}
