package com.example.battleships.service.Impl;

import com.example.battleships.model.entity.User;
import com.example.battleships.model.service.UserServiceModel;
import com.example.battleships.repository.UserRepository;
import com.example.battleships.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        userRepository.save(modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public boolean isUsernameBusy(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailBusy(String email) {
        return userRepository.existsByEmail(email);
    }
}
