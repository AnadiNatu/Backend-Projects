package com.anadi.ServiceBookingSystem.services.authentication;

import com.anadi.ServiceBookingSystem.dto.SignupRequestDto;
import com.anadi.ServiceBookingSystem.dto.UserDto;
import com.anadi.ServiceBookingSystem.entity.User;
import com.anadi.ServiceBookingSystem.enums.UserRole;
import com.anadi.ServiceBookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepository userRepository;

    public UserDto signupClient (SignupRequestDto signupRequestDto){

        User user = new User();

        user.setFname(signupRequestDto.getFname());
        user.setLname(signupRequestDto.getLname());
        user.setPhone(signupRequestDto.getPhone());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder(Integer.parseInt(signupRequestDto.getPassword())).toString());

        user.setRole(UserRole.CLIENT);

        return userRepository.save(user).getDto();

    }

    public Boolean presentByEmail(String email){
        return userRepository.findFirstByEmail(email) != null;
    }

    public UserDto signupCompany (SignupRequestDto signupRequestDto){
        User user = new User();

        user.setFname(signupRequestDto.getFname());
        user.setPhone(signupRequestDto.getPhone());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder(Integer.parseInt(signupRequestDto.getPassword())).toString());

        user.setRole(UserRole.COMPANY);

        return userRepository.save(user).getDto();

    }
}
