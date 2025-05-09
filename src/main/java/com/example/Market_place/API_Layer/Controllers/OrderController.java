package com.example.Market_place.API_Layer.Controllers;


import com.example.Market_place.BLL_Layer.Dto.CreateOrderDTO;
import com.example.Market_place.BLL_Layer.Dto.OrderDTO;
import com.example.Market_place.BLL_Layer.Services.Implementations.OrderService;
import com.example.Market_place.DAL_Layer.Models.Order;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody CreateOrderDTO createOrderDTO) {

        OrderDTO orderDTO = orderService.addOrder(createOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderDTO>> getDeliveredOrders(@PathVariable Long userId) {
        List<Order> deliveredOrders = orderService.findByUserIdAndStatusWithItems(userId, "Delivered");
        List<OrderDTO>orderDTOS=new ArrayList<>();
        for (Order order : deliveredOrders) {
            orderDTOS.add(orderService.mapToOrderDTO(order));
        }
        return ResponseEntity.ok(orderDTOS);
    }
}

