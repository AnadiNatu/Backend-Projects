package com.example.auth_service.controller;

import com.example.auth_service.dto.AuthenticationRequest;
import com.example.auth_service.dto.CustomerDto;
import com.example.auth_service.dto.RegisterDto;
import com.example.auth_service.security.CustomerDetailsService;
import com.example.auth_service.security.JWTGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;


    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest , HttpServletResponse response) throws IOException, JSONException {

        try {
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){

            throw new BadCredentialsException("Inappropriate email or password");
        }

        final UserDetails userDetails = customerDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtGenerator.generateToken(userDetails.getUsername());

        CustomerDetail customer = new CustomerDetail();


        try{
            customer = customerRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        }catch (UsernameNotFoundException ex){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Email not found");

        }


        response.getWriter().write(
                new JSONObject()
                        .put("userId" , customer.getId())
                        .put("role" , customer.getUserRole())
                        .toString());
        response.addHeader("Access-Control-Expose-Headers" , "Authorization");
        response.addHeader("Access-Control-Allow-Headers","Authorization , X-PINGOTHER, Origin , X-Requested-With , Content-Type , X-Custom-header");

        response.addHeader("Authorization" , "Bearer" + jwt);
    }


    @PostMapping("/customer/register")
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterDto registerDto){

        if (customerService.presentByEmail(registerDto.getEmail())){
            return new ResponseEntity<>("Customer with same email exists" , HttpStatus.NOT_ACCEPTABLE);
        }

        CustomerDto customerDto = customerService.registerCustomer(registerDto);

        return new ResponseEntity<>(customerDto , HttpStatus.CREATED);

    }


    @PostMapping("/admin/register")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterDto registerDto){

        if (customerService.presentByEmail(registerDto.getEmail())){

            return new ResponseEntity<>("Admin With this Account Exists", HttpStatus.NOT_ACCEPTABLE);
        }

        CustomerDto customerDto = customerService.registerAdmin(registerDto);

        return new ResponseEntity<>(customerDto,HttpStatus.CREATED);
    }

}
