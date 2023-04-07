package com.example.battleships.service.Impl;

import com.example.battleships.model.entity.User;
import com.example.battleships.model.service.UserServiceModel;
import com.example.battleships.repository.UserRepository;
import com.example.battleships.service.UserService;
import com.example.battleships.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, CurrentUser currentUser, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
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

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {

        return userRepository.findUserByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(UserServiceModel userServiceModel) {

        currentUser.setId(userRepository.findUserByUsername(userServiceModel.getUsername()).getId());
        currentUser.setUsername(userServiceModel.getUsername());
    }

    @Override
    public void logoutUser() {

        currentUser.setId(null);
        currentUser.setUsername(null);
    }
}
