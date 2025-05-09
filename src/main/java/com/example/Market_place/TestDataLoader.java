//package com.example.Market_place;
//
//import com.example.Market_place.DAL_Layer.Models.Item;
//import com.example.Market_place.DAL_Layer.Models.Order;
//import com.example.Market_place.DAL_Layer.Models.User;
//import com.example.Market_place.DAL_Layer.Repositories.Interfaces.ItemRepository;
//
//import com.example.Market_place.DAL_Layer.Repositories.Interfaces.OrderRepository;
//import com.example.Market_place.DAL_Layer.Repositories.Interfaces.UserRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class TestDataLoader implements CommandLineRunner {
//
//    private final OrderRepository orderRepository;
//    private final ItemRepository itemRepository;
//    private final UserRepository userRepository;
//
//    public TestDataLoader(OrderRepository orderRepository, ItemRepository itemRepository, UserRepository userRepository) {
//        this.orderRepository = orderRepository;
//        this.itemRepository = itemRepository;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    @Transactional
//    public void run(String... args) {
//
//        User user1 = new User();
//        user1.setUsername("john_doe@gnail.com");
//        user1.setPassword("password123");
//
//        User seller1 = new User();
//        seller1.setUsername("audiotech@gmail.com");
//        seller1.setPassword("sellerpassword");
//
//        userRepository.addUser(user1);
//        userRepository.addUser(seller1);
//
//        // Order 1
////        Order order1 = new Order();
////        order1.setOrderDate(LocalDate.of(2023, 4, 20));
////        order1.setStatus("delivered");
////        order1.setBuyer(user1);
//
//        Item earbuds = new Item();
//        earbuds.setTitle("Wireless Earbuds");
//        earbuds.setPrice(79.99);
//        earbuds.setSeller(seller1);
//      //  earbuds.setOrder(order1);
//        earbuds.setImage("https://images.unsplash.com/photo-1572569511254-d8f925fe2cbb?auto=format&fit=crop&w=1188&q=80");
//        itemRepository.save(earbuds); // Save item first to make it a managed entity
//
////        order1.setItems(Arrays.asList(earbuds));
////        order1.setTotalPrice(79.99);
////        orderRepository.save(order1); // Save order along with managed items
//
//        // Order 2
////        Order order2 = new Order();
////        order2.setOrderDate(LocalDate.of(2023, 4, 5));
////        order2.setStatus("delivered");
////        order2.setBuyer(user1);
//
//        Item watch = new Item();
//        watch.setTitle("Smart Watch");
//        watch.setPrice(149.99);
//        watch.setSeller(seller1);
//      //  watch.setOrder(order2);
//        watch.setImage("https://images.unsplash.com/photo-1579586337278-3befd40fd17a?auto=format&fit=crop&w=1172&q=80");
//        itemRepository.save(watch); // Save item first
//
//        Item phoneCase = new Item();
//        phoneCase.setTitle("Phone Case");
//        phoneCase.setPrice(19.99);
//        phoneCase.setSeller(seller1);
//      //  phoneCase.setOrder(order2);
//        phoneCase.setImage("https://images.unsplash.com/photo-1601593346740-925612772187?auto=format&fit=crop&w=1170&q=80");
//        itemRepository.save(phoneCase); // Save item first
//
////        order2.setItems(Arrays.asList(watch, phoneCase));
////        order2.setTotalPrice(169.98);
////        orderRepository.save(order2); // Save order along with managed items
//    }
//}