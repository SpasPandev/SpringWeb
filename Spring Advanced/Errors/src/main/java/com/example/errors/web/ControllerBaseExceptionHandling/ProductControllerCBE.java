package com.example.errors.web.ControllerBaseExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductControllerCBE {

    @GetMapping("/cbe/products/{id}/details")
    public String showProductDetails(@PathVariable("id") Long productId) {

        //retrieve product from repository
        //productRepository.findById(productId).orElseThrow(new ProductNotFoundException());
        throw new ObjectNotFoundExceptionCBE(productId);
    }

    @ExceptionHandler(ObjectNotFoundExceptionCBE.class)
    public ModelAndView handleDbException(ObjectNotFoundExceptionCBE e ) {

        ModelAndView modelAndView = new ModelAndView("product-not-found");
        modelAndView.addObject("productId", e.getProductId());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }
}
