package com.example.Market_place.DAL_Layer.DB1.repository;

import com.example.Market_place.DAL_Layer.Models.Order;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepositoryDB1 extends IBaseRepoDB1<com.example.Market_place.DAL_Layer.Models.Order,Long> {
    //@Query("SELECT o FROM Order o LEFT JOIN FETCH o.items WHERE o.buyer.id = :userId AND o.status = :status")
    //List<Order> findByUserIdAndStatusWithItems(@Param("userId") Long userId, @Param("status") String status);
    @EntityGraph(attributePaths = "item")
    List<Order> findByUserIdAndStatus(Long userId, String status);
    Optional<Order> findByPayment_Id(Long paymentId);

}
