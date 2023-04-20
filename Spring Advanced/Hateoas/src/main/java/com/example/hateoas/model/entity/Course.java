package com.example.hateoas.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

    private String name;
    private BigDecimal price;
    private boolean enabled;

    public Course() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public Course setName(String name) {
        this.name = name;
        return this;
    }

    @Column
    public BigDecimal getPrice() {
        return price;
    }

    public Course setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Column
    public boolean isEnabled() {
        return enabled;
    }

    public Course setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
