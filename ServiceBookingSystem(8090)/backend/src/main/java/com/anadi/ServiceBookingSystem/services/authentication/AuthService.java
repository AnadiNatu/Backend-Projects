package com.anadi.ServiceBookingSystem.services.authentication;

import com.anadi.ServiceBookingSystem.dto.SignupRequestDto;
import com.anadi.ServiceBookingSystem.dto.UserDto;

public interface AuthService {

    UserDto signupClient (SignupRequestDto signupRequestDto);

    Boolean presentByEmail(String email);

    UserDto signupCompany (SignupRequestDto signupRequestDto);

}
