package com.example.coffeeshop.service;

import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.model.view.UserViewModel;

import java.util.List;

public interface UserService {

    void registerUser(UserServiceModel userServiceModel);

    boolean isUsernameExist(String username);

    boolean isEmailExist(String email);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(UserServiceModel userServiceModel);

    void logout();

    List<UserViewModel> findAllOrderByEmployeesCountOfOrdersDesc();
}
