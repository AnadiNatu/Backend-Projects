package com.creditcard.system.service;

import com.creditcard.system.dto.RegistrationDto;
import com.creditcard.system.model.AdminDetails;
import com.creditcard.system.model.CustomerDetails;

import java.security.NoSuchAlgorithmException;

public interface RegistrationService {

    public CustomerDetails registerCustomer(RegistrationDto registrationDto) throws IllegalAccessException, NoSuchAlgorithmException;
    public AdminDetails registerAdmin(RegistrationDto registrationDto) throws IllegalAccessException, NoSuchAlgorithmException;
}
