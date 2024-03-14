package com.example.exam2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {
    @NotEmpty(message = "id should not be empty")
    private String id;
    @NotEmpty(message = "name should not be empty")
    private String name;
    @NotNull(message = "age should not be empty")
    private Integer age;
    @NotNull(message = "salary should not be Empty")
    private Double salary;

}
