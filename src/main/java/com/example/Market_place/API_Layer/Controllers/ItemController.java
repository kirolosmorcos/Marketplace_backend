package com.example.Market_place.API_Layer.Controllers;

import com.example.Market_place.BLL_Layer.Dto.UserDTO;
import com.example.Market_place.BLL_Layer.Services.Implementations.ItemService;
import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.DAL_Layer.enums.RoleName;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity<Item> createItem(@RequestBody @Valid Item item) {
        Item savedItem = itemService.save(item);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
        //  return ResponseEntity.ok("Registered!");
    }

}
