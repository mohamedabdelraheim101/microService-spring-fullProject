package com.example.crud_task.controllers;

import com.example.crud_task.Models.DTO.StudentDto;
import com.example.crud_task.Models.Entity.Student;
import com.example.crud_task.Services.StudentService;
import com.example.crud_task.responses.CustomPageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")

public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/allStudents")
    public CustomPageResponse<StudentDto> getAllStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentDto> studentPage = studentService.getAllStudents(name, email, pageable);
        return new CustomPageResponse<>(
                studentPage.getContent(),
                studentPage.getTotalElements(),
                studentPage.getNumberOfElements()
        );
    }


    @PostMapping("/add")
    public StudentDto addStudent(@Valid @RequestBody StudentDto student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/update/{id}")
    public StudentDto updateStudent(@PathVariable int id, @Valid @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "Student with ID " + id + " has been deleted.";
    }
}
