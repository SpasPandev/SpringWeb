package com.example.coffeeshop.web;

import com.example.coffeeshop.model.binding.AddOrderBindingModel;
import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.util.CurrentUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/orders/add")
    public String addOrder() {

        if (!currentUser.isLogedIn()) {
            return "redirect:/";
        }

        return "order-add";
    }

    @PostMapping("/orders/add")
    public String addOrderPost(@Valid AddOrderBindingModel addOrderBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("addOrderBindingModel", addOrderBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOrderBindingModel", bindingResult);

            return "redirect:/orders/add";
        }

        orderService.saveOrder(modelMapper.map(addOrderBindingModel, OrderServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/orders/ready/{id}")
    public String ready(@PathVariable Long id) {

        if (!currentUser.isLogedIn()) {
            return "redirect:/";
        }

        orderService.finishOrder(id);

        return "redirect:/";
    }

    @ModelAttribute
    public AddOrderBindingModel addOrderBindingModel() {
        return new AddOrderBindingModel();
    }
}
