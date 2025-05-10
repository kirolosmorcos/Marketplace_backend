package com.example.Market_place.DAL_Layer.DB2.repository;

import com.example.Market_place.DAL_Layer.Models.Payment;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.IBaseRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepositoryDB2 extends IBaseRepo<Payment,Long> {

    @Query("SELECT MAX(i.paymentId) FROM Payment i ")
    Long findMaxId();
}
