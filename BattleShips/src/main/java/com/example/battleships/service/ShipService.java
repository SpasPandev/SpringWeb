package com.example.battleships.service;

import com.example.battleships.model.service.ShipServiceModel;

import java.util.List;
import java.util.Set;

public interface ShipService {

    boolean isShipNameBusy(String name);

    void addNewShip(ShipServiceModel shipServiceModel);

    Set<ShipServiceModel> findAllAttackerShips(Long id);

    Set<ShipServiceModel> findAllDefendersShips(Long id);

    List<ShipServiceModel> findAllShipsOrderByTheirStatus();

    void fire(String attacker, String defender);
}
