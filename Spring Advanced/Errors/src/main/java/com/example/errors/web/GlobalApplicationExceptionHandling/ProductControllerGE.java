package com.example.errors.web.GlobalApplicationExceptionHandling;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductControllerGE {

    @GetMapping("/ge/products/{id}/details")
    public String showProductsDetails(@PathVariable("id") Long productId) {

        //retrieve product from repository
        //productRepository.findById(productId).orElseThrow(new ProductNotFoundException());
        throw new ObjectNotFoundExceptionGE(productId);
    }
}
