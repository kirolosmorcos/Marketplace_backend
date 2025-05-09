package com.example.Market_place.BLL_Layer.Dto;

import com.example.Market_place.BLL_Layer.Dto.OrderItemDTO;
import lombok.Data;
import java.util.List;

@Data
public class OrderDTO {
    private int  id;
    private String date;
    private List<OrderItemDTO> items;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    private double total;
    private String status;
}

