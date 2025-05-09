package com.example.Market_place.BLL_Layer.Services.Implementations;

import com.example.Market_place.BLL_Layer.Dto.ItemDTO;
import com.example.Market_place.BLL_Layer.Dto.ItemStatisticDTO;
import com.example.Market_place.BLL_Layer.Dto.SpecificationDTO;
import com.example.Market_place.BLL_Layer.Dto.UserItemDTO;
import com.example.Market_place.DAL_Layer.Models.Item;
import com.example.Market_place.DAL_Layer.Models.Order;
import com.example.Market_place.DAL_Layer.Models.User;
import com.example.Market_place.DAL_Layer.Models.Specification;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.ItemRepository;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.OrderRepository;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.SpecificationRepository;
import com.example.Market_place.DAL_Layer.Repositories.Interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService implements com.example.Market_place.BLL_Layer.Services.Interfaces.IItemService {
    @Autowired
    private ItemRepository itemRepo;
    @Autowired
    private SpecificationRepository specificationRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Item save(Item item) {

//        for (Specification spec : item.getSpecifications()) {
//            specificationRepo.save(spec);
//        }
// Save item → automatically saves specifications too
        return itemRepo.save(item);

    }
    @Override
    public void deleteById(Long id) {
        itemRepo.deleteById(id);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepo.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return itemRepo.findAll();
    }
    @Override
    public List<Item> findAllWithSpecifications() {
        return itemRepo.findAllWithSpecifications();
    }

//    public List<UserItemDTO> getItemsByUser(int userId) {
//        return itemRepo.findItemsByUserId(userId);
//    }

    public List<UserItemDTO> getItemsByUser(long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        // Fetch items for the user
        List<Item> items = userOptional.get().getUserListings();

        // Map the Item objects to UserItemDTO
        return items.stream().map(this::mapToUserItemDTO).collect(Collectors.toList());
    }
    public ItemDTO createItemForUser(ItemDTO itemDTO, Long userId) {
        // Retrieve the user by userId
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        Item item = mapToItem(itemDTO);

        item.setSeller(user);

        Item savedItem = save(item);

        return mapToItemDTO(savedItem);
    }


    public ItemStatisticDTO getItemStatisticsForUser(Long userId) {
        // Fetch the items associated with the user
        List<Item> items = itemRepo.findBySellerId(userId); // Assuming you have a method in ItemRepository to find items by seller ID

        // Calculate statistics
        long totalItems = items.stream()
                .mapToLong(Item::getQuantity)  // Get the quantity for each item
                .sum();

        List<Order>orders=orderRepository.findByUserIdAndStatusWithItems(userId,"delivered");
        Long totalOrderItems=0L;
        for(Order order:orders){
            List<Item> Orderitems =order.getItems();

            for(Item item:Orderitems){
                totalOrderItems+=item.getQuantity();
            }


        }
        long soldItems = items.stream()
                .filter(item -> "sold".equalsIgnoreCase(item.getStatus()))  // Filter only the sold items
                .mapToLong(item -> item.getQuantity())  // Map to the quantity of each sold item
                .sum();  // Sum up the quantities to get the total number of sold items

// Calculate total money, including quantity
        double totalMoney = items.stream()
                .filter(item -> "sold".equalsIgnoreCase(item.getStatus()))  // Filter only the sold items
                .mapToDouble(item -> item.getPrice() * item.getQuantity())  // Multiply the price by the quantity for each sold item
                .sum();  // Sum up the total money for all sold items

//        long soldItems = items.stream()
//                .mapToLong(Item::getQuantitySold)  // Get the quantitySold for each item
//                .sum();  // Sum all the sold quantities
//
//// Calculate total money from sold items (using quantitySold and price)
//        double totalMoney = items.stream()
//                .mapToDouble(item -> item.getPrice() * item.getQuantitySold())  // Price * quantitySold for each item
//                .sum();  // Sum all the totals

        // Create and return the statistics DTO
        ItemStatisticDTO statistics = new ItemStatisticDTO();
        statistics.setTotalItems(totalItems);
        statistics.setSoldItems(soldItems);
        statistics.setTotalMoney(totalMoney);
        statistics.setTotalpurchases(totalOrderItems);
        return statistics;
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
       // item.setSeller(user);


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
        //dto.setUserId(item.getSeller().getId());


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
    public UserItemDTO mapToUserItemDTO(Item item) {
        UserItemDTO dto = new UserItemDTO();
        dto.setId(item.getId());  // assuming `id` is Long, convert to String
        dto.setTitle(item.getTitle());
        dto.setPrice(item.getPrice());
        dto.setStatus(item.getStatus());
        dto.setImage(item.getImage());
        dto.setViews(item.getViews());  // Assuming `views` is a property in Item
        //dto.setCreated(item.getDateCreated().toString());// Assuming `created` is a Date, convert it to String
        if (item.getDateCreated() != null) {
            dto.setDateCreated(item.getDateCreated().toString());  // Convert to string
        }

        return dto;
    }
    public Item mapToItemFromUserItemDTO(UserItemDTO userItemDTO) {
        Item item = new Item();
        item.setId(item.getId());  // Assuming the id in UserItemDTO is String, converting to Long
        item.setTitle(userItemDTO.getTitle());
        item.setPrice(userItemDTO.getPrice());
        item.setStatus(userItemDTO.getStatus());
        item.setImage(userItemDTO.getImage());
        item.setViews(userItemDTO.getViews());  // Assuming the views are available in UserItemDTO
       // item.setDateCreated(LocalDate.parse(userItemDTO.getCreated())); // Assuming 'created' is a String in the format yyyy-MM-dd
        if (userItemDTO.getDateCreated() != null) {
            LocalDate dateCreated = LocalDate.parse(userItemDTO.getDateCreated(), DateTimeFormatter.ISO_LOCAL_DATE);
            item.setDateCreated(dateCreated);
        }
        return item;
    }
//    public ItemDTO updateItem(Long itemId, ItemDTO itemDTO) {
//        Optional<Item> existingItemOptional = itemRepo.findById(itemId);
//        if (existingItemOptional.isEmpty()) {
//            throw new RuntimeException("Item not found with ID: " + itemId);
//        }
//
//        Item existingItem = existingItemOptional.get();
//
//        existingItem.setTitle(itemDTO.getTitle());
//        existingItem.setPrice(itemDTO.getPrice());
//        existingItem.setImage(itemDTO.getImage());
//        existingItem.setQuantity(itemDTO.getQuantity());
//        existingItem.setDescription(itemDTO.getDescription());
//        existingItem.setRating(itemDTO.getRating());
//        existingItem.setStatus(itemDTO.getStatus());
/// ////////////////////////////////////////////////////////////////
//        existingItem.getSpecifications().clear();
//        List<Specification> updatedSpecs = new ArrayList<>();
//        for (SpecificationDTO dto : itemDTO.getSpecifications()) {
//            Specification spec = new Specification();
//            spec.setLabel(dto.getLabel());
//            spec.setSpecValue(dto.getValue());
//            spec.setItem(existingItem); // Set back-reference
//            updatedSpecs.add(spec);
//        }
//       // existingItem.setSpecifications(updatedSpecs);

//        // Don't clear specifications
//        List<Specification> existingSpecs = existingItem.getSpecifications();
//        Map<String, Specification> specMap = existingSpecs.stream()
//                .collect(Collectors.toMap(Specification::getLabel, spec -> spec));
//
//// /////////////////////////////////////Track updated labels
//        Set<String> updatedLabels = new HashSet<>();
//
//        for (SpecificationDTO dto : itemDTO.getSpecifications()) {
//            Specification existingSpec = specMap.get(dto.getLabel());
//            if (existingSpec != null) {
//                // Update existing spec
//                existingSpec.setSpecValue(dto.getValue());
//            } else {
//                // Add new spec
//                Specification newSpec = new Specification();
//                newSpec.setLabel(dto.getLabel());
//                newSpec.setSpecValue(dto.getValue());
//                newSpec.setItem(existingItem);
//                existingSpecs.add(newSpec);
//            }
//            updatedLabels.add(dto.getLabel());
//        }
//
//// Remove specs that are no longer present
//        existingSpecs.removeIf(spec -> !updatedLabels.contains(spec.getLabel()));
/// /////////////////////////////////////////////////////////////////////////////////////

//        existingItem.setSeller(existingItem.getSeller());
//        Item savedItem = itemRepo.save(existingItem);
//        return mapToItemDTO(savedItem);
//    }

}
