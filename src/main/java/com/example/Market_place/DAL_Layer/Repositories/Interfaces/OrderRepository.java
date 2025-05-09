package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import com.example.Market_place.DAL_Layer.DB1.repository.OrderRepositoryDB1;
import com.example.Market_place.DAL_Layer.Models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository{
    private OrderRepositoryDB1 OrderRepo1;

   public Order addUser(Order order) {
       return  OrderRepo1.save(order);
   }


    //@GetMapping
  public List<Order> getAllItems() {
       return OrderRepo1.findAll();
  }



    //@GetMapping("/{id}")
   public Order getItem( Long id) {
       return OrderRepo1.findById(id).orElse(null);
   }

    //@DeleteMapping("/{id}")
    public void deleteItem( Long id) {
       OrderRepo1.deleteById(id);
    }
   public List<Order> findByUserIdAndStatusWithItems( Long userId,  String status) {
       return OrderRepo1.findByUserIdAndStatusWithItems(userId, status);
   }

}
