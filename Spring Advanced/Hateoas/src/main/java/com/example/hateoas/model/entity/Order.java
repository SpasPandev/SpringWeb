package com.example.hateoas.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private Course course;
    private Student student;

    public Order() {
    }

    @ManyToOne
    public Course getCourse() {
        return course;
    }

    public Order setCourse(Course course) {
        this.course = course;
        return this;
    }

    @ManyToOne
    public Student getStudent() {
        return student;
    }

    public Order setStudent(Student student) {
        this.student = student;
        return this;
    }
}
