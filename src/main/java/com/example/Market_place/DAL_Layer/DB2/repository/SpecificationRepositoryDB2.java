package com.example.Market_place.DAL_Layer.DB2.repository;

import com.example.Market_place.DAL_Layer.Models.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificationRepositoryDB2 extends IBaseRepoDB2<com.example.Market_place.DAL_Layer.Models.Specification,Long> {
    List<Specification> findByItemId(Long itemId);
}