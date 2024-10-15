package com.web.customer_service.controller;


import com.creditcard.system.dto.RegistrationDto;
import com.creditcard.system.model.AdminDetails;
import com.creditcard.system.model.CustomerDetails;
import com.creditcard.system.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/registration")
@CrossOrigin("*")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/customer")
    public ResponseEntity<String> registerCustomer(@RequestBody RegistrationDto registrationDto) throws NoSuchAlgorithmException, IllegalAccessException {
        CustomerDetails registerCustomer = registrationService.registerCustomer(registrationDto);

        if (registerCustomer != null){
            return new ResponseEntity<>("Customer Added Successful", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Unsuccessful  Addition of Customer Details",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody RegistrationDto registrationDto) throws NoSuchAlgorithmException, IllegalAccessException {
        AdminDetails registerAdmin = registrationService.registerAdmin(registrationDto);

        if (registerAdmin != null){
            return new ResponseEntity<>("Admin Added Successful", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Unsuccessful  Addition of Admin Details",HttpStatus.BAD_REQUEST);
        }
    }

}
