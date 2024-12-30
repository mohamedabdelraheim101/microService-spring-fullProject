package com.example.crud_task.Models.dtoMappers;

import com.example.crud_task.Models.DTO.StudentDto;
import com.example.crud_task.Models.Entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto toDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getNumber(),
                student.getPassword(),
                student.getDepartment() != null ? student.getDepartment().getName() : null
        );
    }

    public Student toEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setNumber(studentDto.getNumber());
        return student;
    }
}
