package com.creditcard.system.controller;


import com.creditcard.system.dto.LoginDto;
import com.creditcard.system.model.AdminDetails;
import com.creditcard.system.model.CustomerDetails;
import com.creditcard.system.repository.AdminRepository;
import com.creditcard.system.repository.CustomerDetailsRepository;
import com.creditcard.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;

    @Autowired
    private AdminRepository adminRepository;


    @PostMapping("/authenticate/customer")
    public ResponseEntity<String> authenticateCustomer(@RequestBody LoginDto loginDto) throws NoSuchAlgorithmException {
        boolean isAuthenticate = loginService.authenticate(loginDto);

        CustomerDetails customer = customerDetailsRepository.findByEmail(loginDto.getEmail());

        if (isAuthenticate){
            return new ResponseEntity<>(customer.getId().toString(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid Credential", HttpStatus.OK);
        }
    }


    @PostMapping("/authenticate/admin")
    public ResponseEntity<String> authenticateAdmin(@RequestBody LoginDto loginDto) throws NoSuchAlgorithmException {
        boolean isAuthenticate = loginService.authenticateAdmin(loginDto);


        if (isAuthenticate){
            return new ResponseEntity<>("Authentication Succesful", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid Credential", HttpStatus.OK);
        }
    }


}
