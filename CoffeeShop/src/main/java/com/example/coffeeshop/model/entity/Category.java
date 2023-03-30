package com.example.coffeeshop.model.entity;

import com.example.coffeeshop.model.enums.CategoryNameEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private CategoryNameEnum name;
    private Integer neededTime;

    public Category() {
    }

    @Enumerated(EnumType.STRING)
    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }

    @Column(nullable = false)
    public Integer getNeededTime() {
        return neededTime;
    }

    public void setNeededTime(Integer neededTime) {
        this.neededTime = neededTime;
    }
}
