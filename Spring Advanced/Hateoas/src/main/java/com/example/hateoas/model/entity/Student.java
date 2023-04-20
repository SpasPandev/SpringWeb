package com.example.hateoas.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    private Integer age;
    private String name;
    private List<Order> orders = new ArrayList<>();

    public Student() {
    }

    @Column(nullable = false)
    public Integer getAge() {
        return age;
    }

    public Student setAge(Integer age) {
        this.age = age;
        return this;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    public List<Order> getOrders() {
        return orders;
    }

    public Student setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
