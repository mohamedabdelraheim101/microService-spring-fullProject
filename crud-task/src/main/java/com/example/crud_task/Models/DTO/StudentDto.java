package com.example.crud_task.Models.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private int id;
    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;
    private int number;
    private String departmentName;
    private String password;

}
