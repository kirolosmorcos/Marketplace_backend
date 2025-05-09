package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import com.example.Market_place.DAL_Layer.DB1.repository.OrderRepositoryDB1;
import com.example.Market_place.DAL_Layer.DB1.repository.PaymentRepositoryDB1;
import com.example.Market_place.DAL_Layer.DB2.repository.OrderRepositoryDB2;
import com.example.Market_place.DAL_Layer.DB2.repository.PaymentRepositoryDB2;
import com.example.Market_place.DAL_Layer.Models.Order;
import com.example.Market_place.DAL_Layer.Models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentRepository {

    @Autowired
    private PaymentRepositoryDB1 PaymentRepo1;
    @Autowired
    private PaymentRepositoryDB2 PaymentRepo2;
    @Autowired
    private OrderRepository OrderRepo;

    public Payment save(Payment payment) {
        //get order by payment id
        payment.setId(getNextId());

        Order order = OrderRepo.getOrderbyPaymentId( payment.getId());
        if (payment.getId()% 2 == 0) {
            return PaymentRepo1.save(payment);  // Even ID → DB1
        } else {
            return PaymentRepo2.save(payment);  // Odd ID → DB2
        }
    }



    public Long getNextId() {
        Long maxId1 = PaymentRepo1.findMaxId();
        Long maxId2 = PaymentRepo2.findMaxId();

        maxId1 = (maxId1 == null) ? 0L : maxId1;
        maxId2 = (maxId2 == null) ? 0L : maxId2;

        return Math.max(maxId1, maxId2) + 1;
    }
}
