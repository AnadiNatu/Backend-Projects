package com.web.customer_service.service;

import com.web.customer_service.dto.LoginDto;
import com.web.customer_service.model.AdminDetails;
import com.web.customer_service.model.CustomerDetails;
import com.web.customer_service.repository.AdminRepository;
import com.web.customer_service.repository.CustomerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public boolean authenticate(LoginDto loginDto) throws NoSuchAlgorithmException {
        return verifyCredentials(loginDto);
    }

    @Override
    public boolean authenticateAdmin(LoginDto loginDto) throws NoSuchAlgorithmException {
        return verifyAdminCredentials(loginDto);
    }

    @Override
    public boolean verifyAdminCredentials(LoginDto loginDto) throws NoSuchAlgorithmException {

        AdminDetails admin = adminRepository.findByEmail(loginDto.getEmail());

        String adminEmail="anadinatu@gmail.com";

        if (admin == null){
            return false;
        }

        return loginDto.getEmail().equals(adminEmail) && admin.getPassword().equals(hashPassword(loginDto.getPassword()));
    }

    @Override
    public boolean verifyCredentials(LoginDto loginDto) throws NoSuchAlgorithmException {

        CustomerDetails customer = customerDetailsRepository.findByEmail(loginDto.getEmail());
        if (customer == null) {
            return false;
        }
        return customer.getPassword().equals(hashPassword(loginDto.getPassword()));
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();


        StringBuilder hexString = new StringBuilder();

        for (byte b : digest){
            hexString.append(String.format("%o2x",b));
        }

        return hexString.toString();
    }
}