package com.example.crud_task.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomPageResponse <T> {
    private List<T> content;
    private long totalElements;
    private int numberOfElements;
}
