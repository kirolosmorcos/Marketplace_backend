package com.example.Market_place.BLL_Layer.Services.Implementations;

import com.example.Market_place.BLL_Layer.Dto.ItemDTO;
import com.example.Market_place.BLL_Layer.Dto.SpecificationDTO;
import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.Specification;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.ItemRepository;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.SpecificationRepository;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService implements com.example.Market_place.BLL_Layer.Services.Interfaces.IItemService {
    @Autowired
    private ItemRepository itemRepo;
    @Autowired
    private SpecificationRepository specificationRepo;

    @Override
    public Item save(Item item) {

//        for (Specification spec : item.getSpecifications()) {
//            specificationRepo.save(spec);
//        }
// Save item → automatically saves specifications too
        return itemRepo.addItem(item);

    }
    @Override
    public void deleteById(Long id) {
        itemRepo.deleteItem(id);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepo.getItem(id);
    }

    @Override
    public List<Item> findAll() {
        return itemRepo.findAll();
    }
    @Override
    public List<Item> findAllWithSpecifications() {
        return itemRepo.findAllWithSpecifications();
    }

    public Item mapToItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setTitle(itemDTO.getTitle());
        item.setPrice(itemDTO.getPrice());
        item.setImage(itemDTO.getImage());
        item.setQuantity(itemDTO.getQuantity());
        item.setDescription(itemDTO.getDescription());
        item.setRating(itemDTO.getRating());
        item.setStatus(itemDTO.getStatus());

        List<Specification> specs = new ArrayList<>();
        for (SpecificationDTO dto : itemDTO.getSpecifications()) {
            Specification spec = new Specification();
            spec.setLabel(dto.getLabel());
            spec.setSpecValue(dto.getValue());
            spec.setItem(item); // IMPORTANT: set back-reference
            specs.add(spec);
        }

        item.setSpecifications(specs);

        return item;
    }
    public ItemDTO mapToItemDTO(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setTitle(item.getTitle());
        dto.setPrice(item.getPrice());
        dto.setImage(item.getImage());
        dto.setQuantity(item.getQuantity());
        dto.setDescription(item.getDescription());
        dto.setRating(item.getRating());
        dto.setStatus(item.getStatus());

        List<SpecificationDTO> specDTOs = new ArrayList<>();
        for (Specification spec : item.getSpecifications()) {
            SpecificationDTO specDTO = new SpecificationDTO();
            specDTO.setLabel(spec.getLabel());
            specDTO.setValue(spec.getSpecValue());
            specDTOs.add(specDTO);
        }

        dto.setSpecifications(specDTOs);

        return dto;
    }
}
