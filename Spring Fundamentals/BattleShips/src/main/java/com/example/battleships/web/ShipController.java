package com.example.battleships.web;

import com.example.battleships.model.binding.ShipAddBindingModel;
import com.example.battleships.model.binding.ShipFireBindingModel;
import com.example.battleships.model.service.ShipServiceModel;
import com.example.battleships.service.ShipService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipController {

    private final ShipService shipService;
    private final ModelMapper modelMapper;

    public ShipController(ShipService shipService, ModelMapper modelMapper) {
        this.shipService = shipService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/ships/add")
    public String addShip() {

        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String addShipConfirm(@Valid ShipAddBindingModel shipAddBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        boolean isNameBusy = shipService.isShipNameBusy(shipAddBindingModel.getName());

        if (bindingResult.hasErrors() || isNameBusy) {

            redirectAttributes.addFlashAttribute("shipAddBindingModel", shipAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipAddBindingModel", bindingResult);

            redirectAttributes.addFlashAttribute("isNameBusy", isNameBusy);

            return "redirect:add";
        }

        shipService.addNewShip(modelMapper.map(shipAddBindingModel, ShipServiceModel.class));

        return "redirect:/";
    }

    @PostMapping("/ships/fire")
    public String fire(@Valid ShipFireBindingModel shipFireBindingModel,
                       BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("shipFireBindingModel", shipFireBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipFireBindingModel", bindingResult);

            redirectAttributes.addFlashAttribute("message", true);
            return "redirect:/";
        }
        shipService.fire(shipFireBindingModel.getAttacker(), shipFireBindingModel.getDefender());

        return "redirect:/";
    }

    @ModelAttribute
    public ShipAddBindingModel shipAddBindingModel() {
        return new ShipAddBindingModel();
    }
}
