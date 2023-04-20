package com.example.hateoas.service.Impl;

import com.example.hateoas.model.dto.StudentDTO;
import com.example.hateoas.model.mapping.StudentMapper;
import com.example.hateoas.repository.StudentRepository;
import com.example.hateoas.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentDTO> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(studentMapper::mapStudentToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO findById(Long id) {

        return studentRepository.findById(id).map(studentMapper::mapStudentToStudentDTO).orElseThrow();
    }

}
