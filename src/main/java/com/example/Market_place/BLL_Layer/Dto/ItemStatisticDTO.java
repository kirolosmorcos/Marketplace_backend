package com.example.Market_place.BLL_Layer.Dto;

public class ItemStatisticDTO {

        private long totalItems;
        private long soldItems;
        private double totalMoney;

        // Getters and setters
        public long getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(long totalItems) {
            this.totalItems = totalItems;
        }

        public long getSoldItems() {
            return soldItems;
        }

        public void setSoldItems(long soldItems) {
            this.soldItems = soldItems;
        }

        public double getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(double totalMoney) {
            this.totalMoney = totalMoney;
        }
    }


