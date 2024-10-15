package com.example.restaurant_service.controller;

import com.example.restaurant_service.dto.OrderResponseDTO;
import com.example.restaurant_service.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping
    public String greetingMessage(){

        return restaurantService.greeting();
    }

    @GetMapping("/order/status/{orderId}")
    public OrderResponseDTO getOrder(@PathVariable String orderId){
        return restaurantService.getOrder(orderId);
    }
}
