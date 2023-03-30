package com.example.coffeeshop.web;

import com.example.coffeeshop.model.binding.UserRegisterBindingModel;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users/register")
    public String register() {

        return "register";
    }

    @PostMapping("/users/register")
    public String registerPost(@Valid UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        boolean passwordsMatch = userRegisterBindingModel.getPassword().equals(
                userRegisterBindingModel.getConfirmPassword());

        boolean isUsernameExist = userService.isUsernameExist(userRegisterBindingModel.getUsername());

        boolean isEmailExist = userService.isEmailExist(userRegisterBindingModel.getEmail());

        if (bindingResult.hasErrors() || !passwordsMatch || isUsernameExist || isEmailExist) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            redirectAttributes.addFlashAttribute("passwordsMatch", passwordsMatch);
            redirectAttributes.addFlashAttribute("isUsernameExist", isUsernameExist);
            redirectAttributes.addFlashAttribute("isEmailExist", isEmailExist);

            return "redirect:register";
        }

        userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:/";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {

        return new UserRegisterBindingModel();
    }
}
