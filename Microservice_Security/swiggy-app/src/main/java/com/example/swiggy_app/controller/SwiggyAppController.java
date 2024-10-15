package com.example.swiggy_app.controller;


import com.example.swiggy_app.dto.OrderResponseDTO;
import com.example.swiggy_app.service.SwiggyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swiggy")
public class SwiggyAppController {

    @Autowired
    private SwiggyService service;

    @GetMapping("/home")
    public String greeting(){

        return service.greeting();

    }

    @GetMapping("/{orderId}")
    public OrderResponseDTO checkOrderStatus(@PathVariable String orderId){

        return service.checkOrderStatus(orderId);

    }


}
