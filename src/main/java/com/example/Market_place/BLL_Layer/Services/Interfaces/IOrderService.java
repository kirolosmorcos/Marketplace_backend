package com.example.Market_place.BLL_Layer.Services.Interfaces;

import com.example.Market_place.DAL_Layer.Models.Order;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderService extends IBaseService<Order,Long>{
    List<Order> findByUserIdAndStatusWithItems( Long userId, String status);
}
