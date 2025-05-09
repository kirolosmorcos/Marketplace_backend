package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import com.example.Market_place.DAL_Layer.DB1.repository.OrderRepositoryDB1;
import com.example.Market_place.DAL_Layer.Models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderRepository{
    @Autowired
    private OrderRepositoryDB1 OrderRepo1;

   public Order save(Order order) {
       return  OrderRepo1.save(order);
   }


    //@GetMapping
  public List<Order> findAll() {
       return OrderRepo1.findAll();
  }



    //@GetMapping("/{id}")
   public Optional<Order> findById(Long id) {
       return OrderRepo1.findById(id);
   }

    //@DeleteMapping("/{id}")
    public void deleteById( Long id) {
       OrderRepo1.deleteById(id);
    }
   public List<Order> findByUserIdAndStatusWithItems( Long userId,  String status) {
       return OrderRepo1.findByUserIdAndStatusWithItems(userId, status);
   }

}
