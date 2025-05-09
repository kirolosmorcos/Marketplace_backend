package com.example.Market_place.BLL_Layer.Services.Interfaces;

import com.example.Market_place.BLL_Layer.Dto.PaymentDTO;

public interface IPaymentService {
    String processPayment(PaymentDTO dto);
}
