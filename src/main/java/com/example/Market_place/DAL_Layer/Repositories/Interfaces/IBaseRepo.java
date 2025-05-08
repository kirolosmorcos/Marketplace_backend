package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBaseRepo <T, ID> extends JpaRepository<T, ID> {
}
