package com.example.Market_place.BLL_Layer.Services.Interfaces;

import com.example.Market_place.DAL_Layer.Models.Item;

import java.util.List;

public interface IItemService extends IBaseService<Item,Long> {

    List<Item> findAllWithSpecifications();
}
