package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import com.example.Market_place.DAL_Layer.DB1.repository.ItemRepositoryDB1;
import com.example.Market_place.DAL_Layer.DB1.repository.SpecificationRepositoryDB1;
import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationRepository{
    @Autowired
    private SpecificationRepositoryDB1 SpecificationRepo1;

    public Specification save(Specification specification) {
        return SpecificationRepo1.save(specification);
    }


    //@GetMapping
    public List<Specification> findAll() {
        return SpecificationRepo1.findAll();
    }



    //@GetMapping("/{id}")
    public Specification findById( Long id) {
        return SpecificationRepo1.findById(id).orElse(null);
    }

    //@DeleteMapping("/{id}")


   public void deleteById( Long id) {
        SpecificationRepo1.deleteById(id);
   }


}

