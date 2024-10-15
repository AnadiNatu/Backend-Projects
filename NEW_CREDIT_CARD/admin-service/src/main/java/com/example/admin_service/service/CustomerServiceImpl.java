package com.example.admin_service.service;

import com.example.admin_service.dtos.CustomerDto;
import com.example.admin_service.enums.UserRole;
import com.example.admin_service.model.CustomerDetail;
import com.example.admin_service.repository.CustomerRepository;
import com.example.admin_service.dtos.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService{


    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto registerCustomer(RegisterDto registerDto){


        CustomerDetail customer = new CustomerDetail();

        customer.setFname(registerDto.getFname());
        customer.setLname(registerDto.getLname());
        customer.setEmail(registerDto.getEmail());
        customer.setPassword(new BCryptPasswordEncoder(Integer.parseInt(registerDto.getPassword())).toString());
        customer.setPhoneNumber(registerDto.getPhoneNumber());
        customer.setAge(registerDto.getAge());
        customer.setUserRole(UserRole.CUSTOMERS);

        return customerRepository.save(customer).getDto();


    }

    @Override
    public CustomerDto registerAdmin(RegisterDto registerDto){

        CustomerDetail customer = new CustomerDetail();

        customer.setFname(registerDto.getFname());
        customer.setLname(registerDto.getLname());
        customer.setEmail(registerDto.getEmail());
        customer.setPassword(new BCryptPasswordEncoder(Integer.parseInt(registerDto.getPassword())).toString());
        customer.setPhoneNumber(registerDto.getPhoneNumber());
        customer.setAge(registerDto.getAge());
        customer.setUserRole(UserRole.ADMIN);

        return customerRepository.save(customer).getDto();

    }

    @Override
    public Boolean presentByEmail(String email){

        return customerRepository.findByEmail(email) != null;
    }




}
