package com.example.Market_place.DAL_Layer.DB1.repository;

import org.springframework.data.jpa.repository.Query;
import com.example.Market_place.DAL_Layer.Models.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificationRepositoryDB1 extends IBaseRepoDB1<com.example.Market_place.DAL_Layer.Models.Specification,Long> {
    List<Specification> findByItemId(Long itemId);

    @Query("SELECT MAX(i.id) FROM Specification i ")
    Long findMaxId();

}
