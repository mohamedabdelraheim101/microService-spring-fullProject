package com.example.crud_task.Services;

import com.example.crud_task.Models.DTO.StudentDto;
import com.example.crud_task.Models.Entity.Student;
import com.example.crud_task.Models.dtoMappers.StudentMapper;
import com.example.crud_task.Repositories.StudentRepo;
import com.example.crud_task.Specifications.StudentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentMapper studentMapper;

    public Page<StudentDto> getAllStudents(String name, String email, Pageable pageable) {
        Page<Student> students = studentRepo.findAll(
                StudentSpecification.filterStudents(name, email), pageable
        );
        return students.map(studentMapper::toDto);
    }

    public StudentDto addStudent(StudentDto studentDto) {
        if (studentRepo.existsByEmail(studentDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Student student = studentMapper.toEntity(studentDto);
        Student savedStudent = studentRepo.save(student);
        return studentMapper.toDto(savedStudent);
    }

    public StudentDto getStudentById(int id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        return studentMapper.toDto(student);
    }

    public StudentDto updateStudent(int id, Student updatedStudent) {
        Student existingStudent = studentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        if (!existingStudent.getEmail().equals(updatedStudent.getEmail()) &&
                studentRepo.existsByEmail(updatedStudent.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setNumber(updatedStudent.getNumber());
        existingStudent.setDepartment(updatedStudent.getDepartment());

        Student savedStudent = studentRepo.save(existingStudent);
        return studentMapper.toDto(savedStudent);
    }



    public void deleteStudent(int id) {
        if (!studentRepo.existsById(id)) {
            throw new IllegalArgumentException("Student not found");
        }
        studentRepo.deleteById(id);
    }
}
