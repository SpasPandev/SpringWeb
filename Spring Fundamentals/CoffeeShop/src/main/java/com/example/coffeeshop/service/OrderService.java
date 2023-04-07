package com.example.coffeeshop.service;

import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {

    List<OrderViewModel> findAllOrdersOrderByPriceDesc();

    void saveOrder(OrderServiceModel orderServiceModel);

    void finishOrder(Long id);
}
