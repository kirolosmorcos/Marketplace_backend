package com.example.Market_place.BLL_Layer.Services.Interfaces;

import java.util.List;
import java.util.Optional;

public interface IBaseService<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
}
