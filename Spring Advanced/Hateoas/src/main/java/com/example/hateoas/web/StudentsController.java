package com.example.hateoas.web;

import com.example.hateoas.model.dto.OrderDTO;
import com.example.hateoas.model.dto.StudentDTO;
import com.example.hateoas.service.OrderService;
import com.example.hateoas.service.StudentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentService studentService;
    private final OrderService orderService;

    public StudentsController(StudentService studentService, OrderService orderService) {
        this.studentService = studentService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> getStudents() {

        List<EntityModel<StudentDTO>> entityModels = studentService.getAllStudents()
                .stream()
                .map(dto -> EntityModel.of(dto, createStudentLinks(dto)))
                .toList();

        return ResponseEntity.ok(CollectionModel.of(entityModels));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDTO>>> getStudentOrders(@PathVariable Long id) {

        List<EntityModel<OrderDTO>> entityModels = orderService.getAllOrdersByStudentId(id)
                .stream()
                .map(EntityModel::of)
                .toList();

        return ResponseEntity.ok(CollectionModel.of(entityModels));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> getStudentById(@PathVariable Long id) {

        StudentDTO studentDTO = studentService.findById(id);

        return ResponseEntity.ok(EntityModel.of(studentDTO, createStudentLinks(studentDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> update(@PathVariable Long id, StudentDTO studentDTO) {

        ///IMPLEMENTATION NOT IMPORTANT for the demo

        return ResponseEntity.ok().build();
    }

    private Link[] createStudentLinks(StudentDTO studentDTO) {

        List<Link> result = new ArrayList<>();

        Link selfLink = linkTo(methodOn(StudentsController.class)
                .getStudentById(studentDTO.getId())).withSelfRel();

        result.add(selfLink);

        Link updateLink = linkTo(methodOn(StudentsController.class)
                .update(studentDTO.getId(), studentDTO)).withRel("update");

        result.add(updateLink);

        Link orderLink = linkTo(methodOn(StudentsController.class)
                .getStudentOrders(studentDTO.getId())).withRel("orders");

        result.add(orderLink);

        return result.toArray(new Link[0]);
    }

}
