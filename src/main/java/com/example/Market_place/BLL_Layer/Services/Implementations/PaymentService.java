package com.example.Market_place.BLL_Layer.Services.Implementations;

import com.example.Market_place.BLL_Layer.Dto.PaymentDTO;
import com.example.Market_place.BLL_Layer.Services.Interfaces.IPaymentService;
import com.example.Market_place.DAL_Layer.Models.Order;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.OrderRepository;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String processPayment(PaymentDTO dto) {
//        Order
//        Optional<User> buyerOpt = userRepository.findById(dto.getBuyerId());
//        Optional<User> sellerOpt = userRepository.findById(dto.getSellerId());
//
//
//        if (buyerOpt.isEmpty() || sellerOpt.isEmpty()) {
//            return "Buyer or Seller not found.";
//        }
//
//        User buyer = buyerOpt.get();
//        User seller = sellerOpt.get();
//
////handling the balance of users
//        if (buyer.getBalance() < dto.getAmount()) {
//            return "Insufficient balance.";
//        }
//
//        buyer.setBalance(buyer.getBalance() - dto.getAmount());
//        seller.setBalance(seller.getBalance() + dto.getAmount());
//
//        userRepository.UpdateUser(buyer);
//        userRepository.UpdateUser(seller);
//
//        //handle order
//
//
//        return "Transaction succeeded! Buyer balance: " + buyer.getBalance() +
//                ", Seller balance: " + seller.getBalance();
  return "Transaction succeeded!";
    }
}
