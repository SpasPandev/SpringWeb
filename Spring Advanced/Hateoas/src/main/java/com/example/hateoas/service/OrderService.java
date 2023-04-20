package com.example.hateoas.service;

import com.example.hateoas.model.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrdersByStudentId(Long studentId);
}
