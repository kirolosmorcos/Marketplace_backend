package com.example.Market_place.DAL_Layer.DB1.repository;

import com.example.Market_place.DAL_Layer.Models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepositoryDB1 extends IBaseRepoDB1<com.example.Market_place.DAL_Layer.Models.Order,Long> {
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.items WHERE o.buyer.id = :userId AND o.status = :status")
    List<Order> findByUserIdAndStatusWithItems(@Param("userId") Long userId, @Param("status") String status);

}
