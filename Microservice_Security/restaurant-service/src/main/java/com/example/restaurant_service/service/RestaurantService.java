package com.example.restaurant_service.service;

import com.example.restaurant_service.dao.RestaurantOrderDAO;
import com.example.restaurant_service.dto.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantOrderDAO orderDAO;

    public String greeting(){
        return "Welcome to swiggy restaurant service";
    }


    public OrderResponseDTO getOrder(String orderId){

        return orderDAO.getOrder(orderId);
    }
}
