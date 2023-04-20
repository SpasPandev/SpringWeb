package com.example.hateoas.service.Impl;

import com.example.hateoas.model.dto.OrderDTO;
import com.example.hateoas.model.mapping.OrderMapper;
import com.example.hateoas.repository.StudentRepository;
import com.example.hateoas.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final StudentRepository studentRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(StudentRepository studentRepository, OrderMapper orderMapper) {
        this.studentRepository = studentRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDTO> getAllOrdersByStudentId(Long studentId) {

        return studentRepository.findById(studentId).get().getOrders()
                .stream()
                .map(orderMapper::mapOrderToOrderDTO)
                .collect(Collectors.toList());
    }
}
