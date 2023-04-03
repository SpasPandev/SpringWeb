package com.example.battleships.service.Impl;

import com.example.battleships.model.entity.Ship;
import com.example.battleships.model.service.ShipServiceModel;
import com.example.battleships.repository.CategoryRepository;
import com.example.battleships.repository.ShipRepository;
import com.example.battleships.repository.UserRepository;
import com.example.battleships.service.ShipService;
import com.example.battleships.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ShipServiceImpl implements ShipService {

    private final ShipRepository shipRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public ShipServiceImpl(ShipRepository shipRepository, CategoryRepository categoryRepository, UserRepository userRepository, CurrentUser currentUser, ModelMapper modelMapper) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isShipNameBusy(String name) {

        return shipRepository.existsByName(name);
    }

    @Override
    public void addNewShip(ShipServiceModel shipServiceModel) {

        Ship ship = modelMapper.map(shipServiceModel, Ship.class);
        ship.setCategory(categoryRepository.findByName(shipServiceModel.getCategory().getName()));
        ship.setUser(userRepository.findById(currentUser.getId()).orElse(null));

        shipRepository.save(ship);
    }
}
