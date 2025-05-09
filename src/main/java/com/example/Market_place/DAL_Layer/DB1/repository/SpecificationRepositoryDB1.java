package com.example.Market_place.DAL_Layer.DB1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepositoryDB1 extends IBaseRepoDB1<com.example.Market_place.DAL_Layer.Models.Specification,Long> {

    @Query("SELECT MAX(i.id) FROM Specification i ")
    Long findMaxId();

}
