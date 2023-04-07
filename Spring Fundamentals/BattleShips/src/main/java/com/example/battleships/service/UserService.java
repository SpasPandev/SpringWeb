package com.example.battleships.service;

import com.example.battleships.model.service.UserServiceModel;

public interface UserService {

    void registerUser(UserServiceModel userServiceModel);

    boolean isUsernameBusy(String username);

    boolean isEmailBusy(String email);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(UserServiceModel userServiceModel);

    void logoutUser();
}
