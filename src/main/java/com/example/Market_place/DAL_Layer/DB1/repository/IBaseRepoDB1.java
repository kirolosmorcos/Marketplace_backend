package com.example.Market_place.DAL_Layer.DB1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean

public interface IBaseRepoDB1<T, ID> extends JpaRepository<T, ID> {
}
