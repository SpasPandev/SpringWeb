package com.example.coffeeshop.service;

import com.example.coffeeshop.model.service.UserServiceModel;

public interface UserService {

    void registerUser(UserServiceModel userServiceModel);

    boolean isUsernameExist(String username);

    boolean isEmailExist(String email);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(UserServiceModel userServiceModel);

    void logout();
}
