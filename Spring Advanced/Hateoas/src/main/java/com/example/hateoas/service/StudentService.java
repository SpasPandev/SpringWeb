package com.example.hateoas.service;

import com.example.hateoas.model.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> getAllStudents();

    StudentDTO findById(Long id);
}
