package com.example.admin_service.service;

import com.example.admin_service.dtos.CustomerDto;
import com.example.admin_service.dtos.RegisterDto;

public interface CustomerService {

    public Boolean presentByEmail(String email);

    public CustomerDto registerAdmin(RegisterDto registerDto);

    public CustomerDto registerCustomer(RegisterDto registerDto);
}
