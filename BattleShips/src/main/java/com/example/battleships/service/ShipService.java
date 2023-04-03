package com.example.battleships.service;

import com.example.battleships.model.service.ShipServiceModel;

public interface ShipService {

    boolean isShipNameBusy(String name);

    void addNewShip(ShipServiceModel shipServiceModel);
}
