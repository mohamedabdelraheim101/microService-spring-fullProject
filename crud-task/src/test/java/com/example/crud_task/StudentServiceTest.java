package com.example.crud_task;

import com.example.crud_task.Models.DTO.StudentDto;
import com.example.crud_task.Models.Entity.Student;
import com.example.crud_task.Models.dtoMappers.StudentMapper;
import com.example.crud_task.Repositories.StudentRepo;
import com.example.crud_task.Services.StudentService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @InjectMocks
    private StudentService studentService; // The service being tested

    @Mock
    private StudentRepo studentRepo; // Mock of the repository

    @Mock
    private StudentMapper studentMapper; // Mock of the mapper

    @Test
    public void testGetStudentById() {
        // Arrange: Set up mock behavior
        int studentId = 1;
        Student mockStudent = new Student();
        mockStudent.setId(studentId);
        mockStudent.setName("John Doe");
        mockStudent.setEmail("john.doe@example.com");

        StudentDto mockStudentDto = new StudentDto(
                studentId, "John Doe", "john.doe@example.com", 123456, null, null
        );

        // Mock the behavior of studentRepo.findById
        when(studentRepo.findById(studentId)).thenReturn(Optional.of(mockStudent));

        // Mock the behavior of studentMapper.toDto
        when(studentMapper.toDto(mockStudent)).thenReturn(mockStudentDto);

        // Act: Call the method being tested
        StudentDto result = studentService.getStudentById(studentId);

        // Assert: Verify the result
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals(studentId, result.getId());
    }



}
