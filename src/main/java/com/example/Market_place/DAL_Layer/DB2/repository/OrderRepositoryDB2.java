package com.example.Market_place.DAL_Layer.DB2.repository;

import com.example.Market_place.DAL_Layer.DB1.repository.IBaseRepoDB1;
import com.example.Market_place.DAL_Layer.Models.Order;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepositoryDB2 extends IBaseRepoDB2<Order,Long> {
    //@EntityGraph(attributePaths = "item")
    //List<Order> findByUserIdAndStatus(Long userId, String status);
    Optional<Order> findByPaymentId(Long paymentId);

    @Query("SELECT MAX(i.orderId) FROM Order i ")
    Long findMaxId();
}