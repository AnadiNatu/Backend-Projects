package com.example.Task_SpringBoot.services.auth;

import com.example.Task_SpringBoot.dto.SignUpRequest;
import com.example.Task_SpringBoot.dto.UserDto;
import com.example.Task_SpringBoot.entities.Users;
import com.example.Task_SpringBoot.enums.UserRole;
import com.example.Task_SpringBoot.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount(){
    Optional<Users> optionalUser =  userRepository.findByUserRole(UserRole.ADMIN);

    if(optionalUser.isEmpty()){

        Users user = new Users();
        user.setEmail("admin@test.com");
        user.setName("Admin");
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        user.setUserRole(UserRole.ADMIN);
        userRepository.save(user);
        System.out.println("Admin is created successfully");

    }
    else {
        System.out.println("Admin already created");
    }
    }

    @Override
    public UserDto signupUser(SignUpRequest signUpRequest) {
    Users user = new Users();

    user.setEmail(signUpRequest.getEmail());
    user.setName(signUpRequest.getName());
    user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
    user.setUserRole(UserRole.EMPLOYEE);
    Users createdUser = userRepository.save(user);
    return createdUser.getDto();
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
