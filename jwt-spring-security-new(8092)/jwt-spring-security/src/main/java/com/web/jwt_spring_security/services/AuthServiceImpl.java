package com.web.jwt_spring_security.services;

import com.web.jwt_spring_security.dtos.SignupDto;
import com.web.jwt_spring_security.dtos.UserDto;
import com.web.jwt_spring_security.models.User;
import com.web.jwt_spring_security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(SignupDto signupDTO) {
        User user = new User();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
        User createdUser = userRepository.save(user);
        UserDto userDTO = new UserDto();
        userDTO.setId(createdUser.getId());
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setName(createdUser.getName());
        return userDTO;
    }
}
