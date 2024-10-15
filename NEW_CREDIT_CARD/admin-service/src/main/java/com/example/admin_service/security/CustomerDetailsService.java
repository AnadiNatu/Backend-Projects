package com.example.admin_service.security;

import com.example.admin_service.enums.UserRole;
import com.example.admin_service.model.CustomerDetail;
import com.example.admin_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomerDetail customer = customerRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));

        return new User(customer.getEmail(), customer.getPassword(), mapRolesToAuthorities(customer.getUserRole()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(UserRole userRole){

        return  List.of(new SimpleGrantedAuthority(userRole.name()));
    }
}
