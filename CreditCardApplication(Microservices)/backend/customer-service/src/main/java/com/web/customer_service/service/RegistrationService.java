package com.web.customer_service.service;


import com.web.customer_service.dto.RegistrationDto;
import com.web.customer_service.model.AdminDetails;
import com.web.customer_service.model.CustomerDetails;

import java.security.NoSuchAlgorithmException;

public interface RegistrationService {

    public CustomerDetails registerCustomer(RegistrationDto registrationDto) throws IllegalAccessException, NoSuchAlgorithmException;
    public AdminDetails registerAdmin(RegistrationDto registrationDto) throws IllegalAccessException, NoSuchAlgorithmException;
}
