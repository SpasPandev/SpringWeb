package com.example.battleships.model.binding;

import com.example.battleships.model.enums.CategoryNameEnum;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ShipAddBindingModel {

    private String name;
    private Long power;
    private Long health;
    private LocalDateTime created;
    private CategoryNameEnum category;

    public ShipAddBindingModel() {
    }

    @NotBlank
    @Size(min = 2, max = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Positive
    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    @NotNull
    @Positive
    public Long getHealth() {
        return health;
    }

    public void setHealth(Long health) {
        this.health = health;
    }

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @NotNull
    public CategoryNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnum category) {
        this.category = category;
    }
}
