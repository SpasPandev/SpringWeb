package com.example.coffeeshop.web;

import com.example.coffeeshop.model.view.OrderViewModel;
import com.example.coffeeshop.model.view.UserViewModel;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import com.example.coffeeshop.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final OrderService orderService;
    private final UserService userService;
    private final CurrentUser currentUser;

    public HomeController(OrderService orderService, UserService userService, CurrentUser currentUser) {
        this.orderService = orderService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String home(Model model) {

        if (currentUser.isLogedIn()) {

            List<OrderViewModel> orders = orderService.findAllOrdersOrderByPriceDesc();

            Integer totalTime = orders
                    .stream()
                    .map(orderViewModel -> orderViewModel.getCategory().getNeededTime())
                    .reduce(Integer::sum)
                    .orElse(0);

            List<UserViewModel> employees = userService.findAllOrderByEmployeesCountOfOrdersDesc();

            model.addAttribute("orders", orders);
            model.addAttribute("totalTime", totalTime);
            model.addAttribute("employees", employees);

            return "home";
        }

        return "index";
    }
}
