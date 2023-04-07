package com.example.battleships.model.binding;

import jakarta.validation.constraints.NotBlank;

public class ShipFireBindingModel {

    private String attacker;
    private String defender;

    public ShipFireBindingModel() {
    }

    @NotBlank
    public String getAttacker() {
        return attacker;
    }

    public void setAttacker(String attacker) {
        this.attacker = attacker;
    }

    @NotBlank
    public String getDefender() {
        return defender;
    }

    public void setDefender(String defender) {
        this.defender = defender;
    }
}
