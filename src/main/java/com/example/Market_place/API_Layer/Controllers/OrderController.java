package com.example.Market_place.API_Layer.Controllers;


import com.example.Market_place.BLL_Layer.Dto.CreateOrderDTO;
import com.example.Market_place.BLL_Layer.Dto.OrderDTO;
import com.example.Market_place.BLL_Layer.Dto.PaymentDTO;
import com.example.Market_place.BLL_Layer.Services.Implementations.OrderService;
import com.example.Market_place.BLL_Layer.Services.Interfaces.IPaymentService;
import com.example.Market_place.DAL_Layer.Models.Order;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(
        origins = "http://localhost:8080",
        methods = { RequestMethod.POST ,RequestMethod.GET,RequestMethod.PUT ,RequestMethod.DELETE, RequestMethod.OPTIONS },
        allowCredentials = "true",
        allowedHeaders="*"
)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String>  addOrder(@RequestBody CreateOrderDTO createOrderDTO) {

        OrderDTO orderDTO = orderService.addOrder(createOrderDTO);
        PaymentDTO paymentDTO=new PaymentDTO();
        paymentDTO.setOrderId(orderDTO.getId());
        String result = paymentService.processPayment(paymentDTO);
        return ResponseEntity.ok(result+orderDTO.getId());
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<OrderDTO>> getDeliveredOrders(@PathVariable Long userId) {
        List<Order> deliveredOrders = orderService.findByUserIdAndStatusWithItems(userId, "Delivered");
        List<OrderDTO>orderDTOS=new ArrayList<>();
        for (Order order : deliveredOrders) {
            orderDTOS.add(orderService.mapToOrderDTO(order));
        }
        return ResponseEntity.ok(orderDTOS);
    }


    @Autowired
    private IPaymentService paymentService;


    public ResponseEntity<String> processPayment(@RequestBody PaymentDTO paymentDTO) {
        String result = paymentService.processPayment(paymentDTO);
        return ResponseEntity.ok(result);
    }
}

