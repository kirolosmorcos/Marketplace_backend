package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import com.example.Market_place.DAL_Layer.DB1.repository.OrderRepositoryDB1;
import com.example.Market_place.DAL_Layer.DB2.repository.OrderRepositoryDB2;
import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.Order;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderRepository{
    @Autowired
    private OrderRepositoryDB1 OrderRepo1;
    @Autowired
    private OrderRepositoryDB2 OrderRepo2;

   public Order save(Order order) {
       if(order.getBuyerId()%2 == 0){
           return  OrderRepo1.save(order);
       }
       return  OrderRepo2.save(order);
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
   public List<Order> findByUserIdAndStatusWithItems( Long userId,  String status) {
//       List<Order> orders = new ArrayList<>();
//       orders.addAll(OrderRepo1.findByUserIdAndStatusWithItems(userId, status));
//       orders.addAll(OrderRepo2.findByUserIdAndStatusWithItems(userId, status));
//
//       return orders;
    //TODO: to be implmented
       return new ArrayList<>();
   }

   public Order getOrderbyPaymentId(Long payId){
//       return orderRepo1.findByPayment_Id(payId)
//               .orElseThrow(() ->
//                       new EntityNotFoundException("No Order found for payment ID " + payId)
//               );
       //TODO: to be implmented
       return  new Order();
   }
}
