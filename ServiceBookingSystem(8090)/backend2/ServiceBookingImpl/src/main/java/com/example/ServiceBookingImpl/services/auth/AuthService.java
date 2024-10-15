package com.example.ServiceBookingImpl.services.auth;

import com.example.ServiceBookingImpl.dto.CompanySignUpDTO;
import com.example.ServiceBookingImpl.dto.SignUpDTO;
import com.example.ServiceBookingImpl.dto.UserDTO;
import com.example.ServiceBookingImpl.entity.Users;
import com.example.ServiceBookingImpl.enums.UserRole;
import com.example.ServiceBookingImpl.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {


    @Autowired
    private UsersRepository usersRepository;

    @PostConstruct
    public void createAdminAccount(){

        Optional<Users> optionalUser = usersRepository.findByRole(UserRole.ADMIN);

        if (optionalUser.isEmpty()){

            Users user = new Users();
            user.setUsername("admin@test.com");
            user.setName("Admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setRole(UserRole.ADMIN);
            usersRepository.save(user);
            System.out.println("Admin is created successfully");
        }
        else {
            System.out.println("Admin already created");
        }
    }

    public UserDTO signupCompany(CompanySignUpDTO signUpDTO){


        Optional<Users> companyUser = usersRepository.findByUsername(signUpDTO.getUsername());

        if (companyUser.isEmpty()){

            Users users = new Users();
            users.setName(signUpDTO.getCompanyName());
            users.setPhone(signUpDTO.getPhone());
            users.setUsername(signUpDTO.getUsername());
            users.setPassword(new BCryptPasswordEncoder().encode(signUpDTO.getPassword()));
            users.setRole(UserRole.COMPANY);

            Users companyCreated = usersRepository.save(users);


            return companyCreated.getDTO();
        }

        return null;

    }

    public UserDTO signupClient(SignUpDTO signUpDTO){

        Optional<Users> clientUser = usersRepository.findByUsername(signUpDTO.getUsername());

        if (clientUser.isEmpty()){

            Users users = new Users();
            users.setName(signUpDTO.getName());
            users.setPhone(signUpDTO.getPhone());
            users.setUsername(signUpDTO.getUsername());
            users.setPassword(new BCryptPasswordEncoder().encode(signUpDTO.getPassword()));
            users.setRole(UserRole.CLIENT);

            Users clientCreated = usersRepository.save(users);


            return clientCreated.getDTO();
        }

        return null;

    }

    public boolean hasUserWithUsername(String username){return usersRepository.findByUsername(username).isPresent() ;}



}
