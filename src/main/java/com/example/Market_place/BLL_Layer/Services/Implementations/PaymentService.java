package com.example.Market_place.BLL_Layer.Services.Implementations;

import com.example.Market_place.BLL_Layer.Dto.PaymentDTO;
import com.example.Market_place.BLL_Layer.Services.Interfaces.IPaymentService;
import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.Order;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.OrderRepository;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.PaymentRepository;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.UserRepository;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public String processPayment(PaymentDTO dto) {
        Order order=orderRepository.findByIdWithItems(dto.getOrderId());

        Optional<User> buyerOpt = userRepository.findById(order.getBuyerId());
        List<User> sellers =new ArrayList<>();
        for(Item item:order.getItems()){
            sellers.add(item.getSeller());
        }


        if (buyerOpt.isEmpty() || sellers.isEmpty()) {
            return "Buyer or Seller not found.";
        }

        double totalPrice=order.getTotalPrice();
        if(buyerOpt.get().getBalance()<totalPrice){
            return "Insufficient balance.";
        }

        User buyer = buyerOpt.get();
        for(Item item:order.getItems()){

            User seller = item.getSeller();
            buyer.setBalance(buyer.getBalance() -item.getPrice() );
            seller.setBalance(seller.getBalance() + item.getPrice());

            userRepository.UpdateUser(buyer);
            userRepository.UpdateUser(seller);

        }



//handling the balance of users

        //handle order

       order.setStatus("delivered");
        for(Item item:order.getItems()){
            item.setStatus("sold");
        }
        return "Transaction succeeded! ";

    }
}
