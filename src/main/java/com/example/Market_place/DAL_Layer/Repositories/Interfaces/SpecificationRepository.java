package com.example.Market_place.DAL_Layer.Repositories.Interfaces;

import com.example.Market_place.DAL_Layer.DB1.repository.ItemRepositoryDB1;
import com.example.Market_place.DAL_Layer.DB1.repository.SpecificationRepositoryDB1;
import com.example.Market_place.DAL_Layer.DB2.repository.SpecificationRepositoryDB2;
import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.Specification;
import com.example.Market_place.DAL_Layer.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpecificationRepository{
    @Autowired
    private ItemRepository itemRepo;         // or your sharding ItemRepository

    @Autowired
    private SpecificationRepositoryDB1 SpeciRepo1;
    @Autowired
    private SpecificationRepositoryDB2 SpeciRepo2;

    public Specification save(Specification specification) {
        specification.setId(getNextId());

        Long itemId = specification.getItemId();

        // 1) Load the item (or throw if not found)
        Item item = itemRepo.findById(itemId).orElseThrow(() ->
                new IllegalArgumentException("Item not found for id: " + itemId)
        );

        if(item.getSellerId()%2==0){
            return SpeciRepo1.save(specification);
        }
        return SpeciRepo2.save(specification);
    }


    //@GetMapping
    public List<Specification> findAll() {
        List<Specification> allSpecis = new ArrayList<>();
        allSpecis.addAll(SpeciRepo1.findAll());
        allSpecis.addAll(SpeciRepo2.findAll());
        return allSpecis;
    }



    //@GetMapping("/{id}")
    public Specification findById( Long id) {
        Specification speci1 = SpeciRepo1.findById(id).orElse(null);
        if (speci1==null) {
            Specification speci2 = SpeciRepo2.findById(id).orElse(null);
            return speci2;
        }
        ;
        return speci1;
    }

    //@DeleteMapping("/{id}")


   public void deleteById( Long id) {
       Specification one = SpeciRepo1.findById(id).orElse(null);
       Specification two = SpeciRepo2.findById(id).orElse(null);
       if (one != null ) {
           SpeciRepo1.deleteById(id);
       }
       if (two != null ) {
           SpeciRepo2.deleteById(id);
       }
   }

    public Long getNextId() {
        Long maxId1 = SpeciRepo1.findMaxId();
        Long maxId2 = SpeciRepo2.findMaxId();

        maxId1 = (maxId1 == null) ? 0L : maxId1;
        maxId2 = (maxId2 == null) ? 0L : maxId2;

        return Math.max(maxId1, maxId2) + 1;
    }
}

