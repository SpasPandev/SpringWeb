package com.example.battleships.web;

import com.example.battleships.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    public HomeController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String home() {

        if (currentUser.isLoggedIn()) {


            return "home";
        }

        return "index";
    }
}
