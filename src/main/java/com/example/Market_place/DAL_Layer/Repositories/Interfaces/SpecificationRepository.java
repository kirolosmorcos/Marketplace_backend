package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import com.example.Market_place.DAL_Layer.DB1.repository.ItemRepositoryDB1;
import com.example.Market_place.DAL_Layer.DB1.repository.SpecificationRepositoryDB1;
import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpecificationRepository{
    private SpecificationRepositoryDB1 SpecificationRepo1;

    public Specification addUser(Specification specification) {
        return SpecificationRepo1.save(specification);
    }


    //@GetMapping
    public List<Specification> getAllItems() {
        return SpecificationRepo1.findAll();
    }



    //@GetMapping("/{id}")
    public Specification getItem( Long id) {
        return SpecificationRepo1.findById(id).orElse(null);
    }

    //@DeleteMapping("/{id}")


   public void deleteItem( Long id) {
        SpecificationRepo1.deleteById(id);
   }


}

