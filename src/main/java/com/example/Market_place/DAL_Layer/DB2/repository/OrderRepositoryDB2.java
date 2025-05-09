package com.example.Market_place.DAL_Layer.DB2.repository;

import com.example.Market_place.DAL_Layer.DB1.repository.IBaseRepoDB1;
import com.example.Market_place.DAL_Layer.Models.Order;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepositoryDB2 extends IBaseRepoDB2<Order,Long> {

}