package com.example.hateoas.model.mapping;

import com.example.hateoas.model.dto.OrderDTO;
import com.example.hateoas.model.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    default OrderDTO mapOrderToOrderDTO(Order order){

        if (order == null){
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setStudentId(order.getStudent().getId());
        orderDTO.setCourseId(order.getCourse().getId());

        return orderDTO;
    }
}
