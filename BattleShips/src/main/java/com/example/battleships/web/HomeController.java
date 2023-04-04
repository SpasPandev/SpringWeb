package com.example.battleships.web;

import com.example.battleships.model.binding.ShipFireBindingModel;
import com.example.battleships.model.service.ShipServiceModel;
import com.example.battleships.service.ShipService;
import com.example.battleships.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final ShipService shipService;

    public HomeController(CurrentUser currentUser, ShipService shipService) {
        this.currentUser = currentUser;
        this.shipService = shipService;
    }

    @GetMapping("/")
    public String home(Model model) {

        if (currentUser.isLoggedIn()) {

            Set<ShipServiceModel> attackerShips = shipService.findAllAttackerShips(currentUser.getId());
            Set<ShipServiceModel> defendersShips = shipService.findAllDefendersShips(currentUser.getId());
            List<ShipServiceModel> allShips = shipService.findAllShipsOrderByTheirStatus();

            model.addAttribute("attackerShips", attackerShips);
            model.addAttribute("defendersShips", defendersShips);
            model.addAttribute("allShips", allShips);

            return "home";
        }

        return "index";
    }

    @ModelAttribute
    public ShipFireBindingModel shipFireBindingModel() {
        return new ShipFireBindingModel();
    }
}
