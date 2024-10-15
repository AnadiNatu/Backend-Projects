package com.example.ServiceBookingImpl.dto;

import lombok.Data;

@Data
public class CompanySignUpDTO {

    private Long id;
    private String companyName;
    private String username;
    private String password;
    private String phone;

}
