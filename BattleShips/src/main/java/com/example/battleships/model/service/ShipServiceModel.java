package com.example.battleships.model.service;

import com.example.battleships.model.entity.Category;
import com.example.battleships.model.entity.User;

import java.time.LocalDateTime;

public class ShipServiceModel {

    private String name;
    private Long health;
    private Long power;
    private LocalDateTime created;
    private Category category;
    private User user;

    public ShipServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHealth() {
        return health;
    }

    public void setHealth(Long health) {
        this.health = health;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
