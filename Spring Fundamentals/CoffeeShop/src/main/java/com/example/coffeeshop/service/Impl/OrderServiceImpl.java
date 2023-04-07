package com.example.coffeeshop.service.Impl;

import com.example.coffeeshop.model.entity.Order;
import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.model.view.OrderViewModel;
import com.example.coffeeshop.repository.CategoryRepository;
import com.example.coffeeshop.repository.OrderRepository;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, CategoryRepository categoryRepository, CurrentUser currentUser, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderViewModel> findAllOrdersOrderByPriceDesc() {

        return orderRepository.findAllByOrderByPriceDesc()
                .stream()
                .map(order -> modelMapper.map(order, OrderViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void saveOrder(OrderServiceModel orderServiceModel) {

        Order order = modelMapper.map(orderServiceModel, Order.class);
        order.setCategory(categoryRepository.findByName(orderServiceModel.getCategory()));
        order.setEmployee(userRepository.findById(currentUser.getId()).orElse(null));

        orderRepository.save(order);
    }

    @Override
    public void finishOrder(Long id) {

        orderRepository.deleteById(id);
    }
}
