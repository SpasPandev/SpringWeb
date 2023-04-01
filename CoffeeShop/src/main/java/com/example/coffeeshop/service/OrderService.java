package com.example.coffeeshop.service;

import com.example.coffeeshop.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {

    List<OrderViewModel> findAllOrdersOrderByPriceDesc();
}
