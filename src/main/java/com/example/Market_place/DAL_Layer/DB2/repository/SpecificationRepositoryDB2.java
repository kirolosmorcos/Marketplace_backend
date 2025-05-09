package com.example.Market_place.DAL_Layer.DB2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepositoryDB2 extends IBaseRepoDB2<com.example.Market_place.DAL_Layer.Models.Specification,Long> {

    @Query("SELECT MAX(i.id) FROM Specification i ")
    Long findMaxId();
}