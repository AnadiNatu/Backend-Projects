package com.example.ServiceBookingImpl.dto;

import lombok.Data;

@Data
public class SignUpDTO {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String phone;

}
