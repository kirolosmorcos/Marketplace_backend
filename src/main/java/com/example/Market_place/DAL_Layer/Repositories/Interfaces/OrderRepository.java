package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import com.example.Market_place.DAL_Layer.DB1.repository.ItemRepositoryDB1;
import com.example.Market_place.DAL_Layer.DB1.repository.OrderRepositoryDB1;
import com.example.Market_place.DAL_Layer.DB2.repository.OrderRepositoryDB2;
import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.Order;
import com.example.Market_place.DAL_Layer.Models.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderRepository{
    @Autowired
    private OrderRepositoryDB1 OrderRepo1;
    @Autowired
    private OrderRepositoryDB2 OrderRepo2;
    @Autowired
    private UserRepository UserRepo;

    @Autowired
    private ItemRepository itemRepo;

    public Order save(Order order) {

       if(order.getBuyerId()%2 == 0){
           return  OrderRepo1.save(order);
       }
       return  OrderRepo2.save(order);
   }

//TODO: check id before saving
    public boolean idExists(Order order){
       if(OrderRepo1.findById(order.getOrderId()).isPresent()){
           return false;
        }
        if (OrderRepo1.findById(order.getOrderId()).isPresent()){
            return false;
        }
        return true;
    }
    //@GetMapping
  public List<Order> findAll() {
      List<Order> allOrders = new ArrayList<>();
      allOrders.addAll(OrderRepo1.findAll());
      allOrders.addAll(OrderRepo2.findAll());
      return allOrders;
  }



    //@GetMapping("/{id}")
   public Optional<Order> findById(Long id) {
       Optional<Order> order = OrderRepo1.findById(id);
       if (order.isPresent()) {
           return order;
       }
       Optional<Order> order2 = OrderRepo2.findById(id);
       return order2;
   }

    //@DeleteMapping("/{id}")
    public void deleteById( Long id) {
        Order one = OrderRepo1.findById(id).orElse(null);
        Order two = OrderRepo2.findById(id).orElse(null);
        if (one != null ) {
            OrderRepo1.deleteById(id);
        }
        if (two != null ) {
            OrderRepo2.deleteById(id);
        }
    }

   public List<Order> findByUserIdAndStatusWithItems( Long userId,  String status) {//need implementation with join
       List<Order> allOrders = new ArrayList<>();

       User user = UserRepo.findById(userId).orElse(null);
       allOrders = user.getOrders();
       List<Order> filteredOrders = allOrders.stream()
               .filter(order -> status.equals(order.getStatus()))
               .collect(Collectors.toList());
       for(Order order:filteredOrders)
       {
           List<Item> items=new ArrayList<>();
           items.addAll(itemRepo.findByOrderId(order.getOrderId()));
           order.setItems(items);
       }

       return filteredOrders;
   }

   public Order getOrderbyPaymentId(Long payId){
       Optional<Order> order = OrderRepo1.findByPaymentId(payId);
       if (order.isPresent()) {
           return order.get();
       }

       // Fallback to DB2
       Optional<Order> order2 = OrderRepo2.findByPaymentId(payId);
       return order2.get();

   }
}
