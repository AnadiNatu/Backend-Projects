package com.anadi.ServiceBookingSystem.dto;

import com.anadi.ServiceBookingSystem.enums.UserRole;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class SignupRequestDto {

    private Long id;

    private String password;

    private String fname;

    private String lname;

    private String phone;

    private String email;


}
