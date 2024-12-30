package com.example.crud_task.controllers;

import com.example.crud_task.Models.DTO.DepartmentDto;
import com.example.crud_task.Services.DepartmentService;
import com.example.crud_task.responses.CustomPageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/allDepartments")
    public CustomPageResponse<DepartmentDto> getAllDepartments(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page <DepartmentDto> departmentPage= departmentService.getAllDepartments(name, pageable);
        return new CustomPageResponse<>(
                departmentPage.getContent(),
                departmentPage.getTotalElements(),
                departmentPage.getNumberOfElements()
        );
    }

    @PostMapping("/add")
    public DepartmentDto addDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        return departmentService.addDepartment(departmentDto);
    }

    @GetMapping("/{id}")
    public DepartmentDto getDepartmentById(@PathVariable int id) {
        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/update/{id}")
    public DepartmentDto updateDepartment(@PathVariable int id, @Valid @RequestBody DepartmentDto departmentDto) {
        return departmentService.updateDepartment(id, departmentDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return "Department with ID " + id + " has been deleted.";
    }
}
