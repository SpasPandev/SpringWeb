package com.example.coffeeshop.service.Impl;

import com.example.coffeeshop.model.entity.User;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.service.UserService;
import com.example.coffeeshop.util.CurrentUser;
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

        User user = modelMapper.map(userServiceModel, User.class);

        userRepository.save(user);
    }

    @Override
    public boolean isUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {

        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(UserServiceModel userServiceModel) {

        currentUser.setId(userServiceModel.getId());
        currentUser.setUsername(userServiceModel.getUsername());
    }

    @Override
    public void logout() {

        currentUser.setId(null);
        currentUser.setUsername(null);
    }
}
