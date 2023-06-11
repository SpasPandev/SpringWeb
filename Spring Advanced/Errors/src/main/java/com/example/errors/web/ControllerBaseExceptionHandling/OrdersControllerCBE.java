package com.example.errors.web.ControllerBaseExceptionHandling;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrdersControllerCBE {

    @GetMapping("/cbe/orders/{id}/details")
    public String showOrdersDetails(@PathVariable("id") Long orderId) {

        throw new ObjectNotFoundExceptionCBE(orderId);
    }
}
