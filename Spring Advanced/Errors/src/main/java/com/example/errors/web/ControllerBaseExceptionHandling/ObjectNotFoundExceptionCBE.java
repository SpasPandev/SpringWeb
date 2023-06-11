package com.example.errors.web.ControllerBaseExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found!")
public class ObjectNotFoundExceptionCBE extends RuntimeException{

    private final Long productId;

    public ObjectNotFoundExceptionCBE(Long productId) {
        super("Product with id " + productId + " not found!");
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
