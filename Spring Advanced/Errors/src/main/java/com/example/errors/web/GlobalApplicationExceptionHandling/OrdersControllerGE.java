package com.example.errors.web.GlobalApplicationExceptionHandling;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrdersControllerGE {

    @GetMapping("/ge/orders/{id}/details")
    public String showOrderDetails(@PathVariable("id") Long orderId){

        throw new ObjectNotFoundExceptionGE(orderId);
    }
}
