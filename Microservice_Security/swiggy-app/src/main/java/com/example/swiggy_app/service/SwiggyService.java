package com.example.swiggy_app.service;

import com.example.swiggy_app.client.RestaurantServiceClient;
import com.example.swiggy_app.dto.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwiggyService {

    @Autowired
    private RestaurantServiceClient restaurantServiceClient;


    public String greeting(){
        return "Welcome to Swiggy App Service";
    }


    public OrderResponseDTO checkOrderStatus(String orderId){
        return restaurantServiceClient.fetchOrderStatus(orderId);
    }

}
