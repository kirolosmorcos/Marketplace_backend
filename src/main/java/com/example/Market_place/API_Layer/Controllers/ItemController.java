package com.example.Market_place.API_Layer.Controllers;

import com.example.Market_place.BLL_Layer.Dto.ItemDTO;
import com.example.Market_place.BLL_Layer.Dto.ItemStatisticDTO;
import com.example.Market_place.BLL_Layer.Dto.UserDTO;
import com.example.Market_place.BLL_Layer.Dto.UserItemDTO;
import com.example.Market_place.BLL_Layer.Services.Implementations.ItemService;
import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.UserRepository;
import com.example.Market_place.DAL_Layer.enums.RoleName;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private UserRepository userRepository;

    //    @PostMapping("/add")
//    public ResponseEntity<ItemDTO> createItem(@RequestBody @Valid ItemDTO itemDTO) {
//        Item item = itemService.mapToItem(itemDTO);
//        Item savedItem = itemService.save(item);
//        ItemDTO itemDTO1 = itemService.mapToItemDTO(savedItem);
//        return ResponseEntity.status(HttpStatus.CREATED).body(itemDTO1);
//
//    }
@PostMapping("/add/{userId}")
public ResponseEntity<ItemDTO> createItem(@PathVariable Long userId, @RequestBody @Valid ItemDTO itemDTO) {

    ItemDTO savedItemDTO = itemService.createItemForUser(itemDTO, userId);


    return ResponseEntity.status(HttpStatus.CREATED).body(savedItemDTO);
}



    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        List<Item> items = itemService.findAllWithSpecifications();
        List<ItemDTO>itemsDTO=new ArrayList<>();
        for (Item item : items) {
            itemsDTO.add(itemService.mapToItemDTO(item));
        }
        return ResponseEntity.ok(itemsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Long id) {
        Optional<Item> itemOptional = itemService.findById(id);
        Optional<ItemDTO> itemDTOOptional = itemOptional.map(itemService::mapToItemDTO);
        return itemDTOOptional
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/hi")
    public String logout() {
        return "Logout successful!";
    }

    @GetMapping("/{userId}/items")
    public ResponseEntity<List<UserItemDTO>> getUserItems(@PathVariable int userId) {
        List<UserItemDTO> items = itemService.getItemsByUser(userId);
        return ResponseEntity.ok(items);
    }
    @GetMapping("/statistics/{userId}")
    public ItemStatisticDTO getItemStatistics(@PathVariable Long userId) {
        // Get the item statistics for the user
        return itemService.getItemStatisticsForUser(userId);
    }

}


