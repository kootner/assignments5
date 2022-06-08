package com.sparta.assignments5.controller;

import com.sparta.assignments5.dto.OrderDto;
import com.sparta.assignments5.dto.OrderRequestDto;
import com.sparta.assignments5.model.OrderData;
import com.sparta.assignments5.repository.OrderDataRepository;
import com.sparta.assignments5.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    private final OrderDataRepository orderDataRepository;

    @PostMapping("/order/request")
    public OrderDto order(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.orderRequest(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderData> orderList () {
        return orderDataRepository.findAll();
    }
}
