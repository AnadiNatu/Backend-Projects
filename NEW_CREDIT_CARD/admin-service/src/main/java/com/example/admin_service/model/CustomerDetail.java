package com.example.admin_service.model;


import com.example.admin_service.dtos.CustomerDto;
import com.example.admin_service.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Customer_Details")
public class CustomerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fname;
    private String lname;
    private String phoneNumber;
    private String email;
    private String password;
    private String age;

    private UserRole userRole;

    @OneToMany(mappedBy = "customerDetail" , cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<CardDetails> cardList = new ArrayList<>();

    @OneToMany(mappedBy = "customerDetails" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Transactions> transactions = new ArrayList<>();


    public CustomerDto getDto(){

        CustomerDto userDto = new CustomerDto();
        userDto.setId(id);
        userDto.setFname(fname);
        userDto.setLname(lname);
        userDto.setPhoneNumber(phoneNumber);
        userDto.setEmail(email);
        userDto.setAge(age);
        userDto.setUserRole(userRole);

        return userDto;

    }
}
