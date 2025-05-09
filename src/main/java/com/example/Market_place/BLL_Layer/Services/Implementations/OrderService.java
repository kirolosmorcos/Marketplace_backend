package com.example.Market_place.BLL_Layer.Services.Implementations;

import com.example.Market_place.BLL_Layer.Dto.CreateOrderDTO;
import com.example.Market_place.BLL_Layer.Dto.OrderDTO;
import com.example.Market_place.BLL_Layer.Dto.OrderItemDTO;
import com.example.Market_place.BLL_Layer.Services.Interfaces.IBaseService;
import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.Order;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.ItemRepository;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IBaseService<Order,Long> {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private UserService userService;
    @Autowired
    private ItemRepository itemRepo;

    public List<Order> findByUserIdAndStatusWithItems(Long userId, String status)
    {
        List<Order> deliveredOrders = orderRepo.findByUserIdAndStatusWithItems(userId, "delivered");
        return deliveredOrders;
    }

    public OrderDTO addOrder(CreateOrderDTO createOrderDTO) {
        Order order =new Order();
        order.setOrderDate(createOrderDTO.getOrderDate());
        order.setStatus(createOrderDTO.getStatus());  //mohm for checking
        User user = userService.findById(createOrderDTO.getUserId()).get();

        order.setBuyer(user);
        order.setBuyerId(user.getId());

        order.setTotalPrice(0.0);


        List<Item >items=new ArrayList<>();

        for(Long itemId:createOrderDTO.getItemIds()){
            Item item =itemRepo.findById(itemId).get();
            item.setQuantity(item.getQuantity()-1);

            item.setOrder(order);
            item.setOrderId(order.getOrderId());
            itemRepo.UpdateItem(item);

            items.add(item);
            order.setTotalPrice(order.getTotalPrice()+item.getPrice());
        }
        order.setItems(items);
         orderRepo.save(order);
        return mapToOrderDTO(order);
    }

    public OrderDTO mapToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getOrderId());
        orderDTO.setDate(order.getOrderDate().toString());  // Assuming `date` is a `LocalDate` or `LocalDateTime`
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotal(order.getTotalPrice());

        List<OrderItemDTO> itemDTOs = order.getItems().stream()
                .map(item -> {
                    OrderItemDTO itemDTO = new OrderItemDTO();
                    itemDTO.setId(item.getId());
                    itemDTO.setTitle(item.getTitle());
                    itemDTO.setPrice(item.getPrice());

                    itemDTO.setSeller(item.getSeller().getUsername());
                    itemDTO.setImage(item.getImage());
                    return itemDTO;
                })
                .collect(Collectors.toList());

        orderDTO.setItems(itemDTOs);

        return orderDTO;
    }
    @Override
    public Order save(Order order) {
        return orderRepo.save(order);
    }
    @Override
    public  List<Order> findAll() {
        return orderRepo.findAll();
    }
    @Override
    public Optional<Order>findById(Long id) {
        return orderRepo.findById(id);
    }
    @Override
    public void deleteById(Long id) {
        orderRepo.deleteById(id);
    }

}
