package com.example.hateoas.model.dto;

public class StudentDTO {

    private Long id;
    private String name;
    private Integer age;

    public StudentDTO() {
    }

    public Long getId() {
        return id;
    }

    public StudentDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public StudentDTO setAge(Integer age) {
        this.age = age;
        return this;
    }
}
