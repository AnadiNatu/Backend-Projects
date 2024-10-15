package com.example.NewSecurityJwt.controller;

import com.example.NewSecurityJwt.dto.AuthRequest;
import com.example.NewSecurityJwt.dto.Product;
import com.example.NewSecurityJwt.entity.UserInfo;
import com.example.NewSecurityJwt.service.JwtService;
import com.example.NewSecurityJwt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController{
    @Autowired
    private ProductService service;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PreAuthorize("hasRole()")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
      try {
          Product product = service.getProduct(id);
          System.out.println(product);
          return ResponseEntity.ok(product);
      }catch (Exception e){
          return ResponseEntity.notFound().build();
      }
    }


    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }
}

//{
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private JwtService jwtService;
//
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @GetMapping("/welcome")
//    public String welcome(){
//        return "Welcome this endpoint is not secure";
//    }
//
//
//    @PostMapping("/new")
//    public String addNewUser(@RequestBody UserInfo userInfo){
//        return productService.addUser(userInfo);
//    }
//
//    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // What this annotation does is that it checks the accesicibilty of the api request by checking authority of the user
//    public List<Product> getAllProducts(){
//        return  productService.getProducts();
//    }
//
//
//    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public Product getProductById(@PathVariable int id){
//        return productService.getProduct(id);
//    }
//
//
//    @PostMapping("/authenticate")
//    public String authenticateToken(@RequestBody AuthRequest authRequest){
//
//        // We should provide there username and password the first time and create a token based on their credentials
//        // Now with username&password in authRequest we will give this to jwt services and ask for a jwt
//
//        // Before we allow that user to access the urls we need to authenticate , so we need a authenticationManager that will validate/authenticate the user .
//        // it will return us an authentication object
//
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername() ,authRequest.getPassword())) ;
//        // (8)
//        // This line of code is where Spring Security takes the user's login information(username & password) and checks if it matches what's stored in the system
//        // If it does , user is considered logged in and can access secure parts of the application
//        // Flow - Found in login or authentication controllers , where the login request is processed. Upon successful authentication , Spring Security upadtes the security context with the authenticated 'Authentication' object , making use details available throughout the session
//        // Working - This line of code is used to authenticate a user by verifying their credentials (username and password). It is a critical step in determining if a user is who they claim to be.
//        // UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()) creates an authentication token using the username and password provided by the user. This token represents the user's credentials.
//
//        if (authentication.isAuthenticated()){
//            return jwtService.generateToken(authRequest.getUsername());
//        }else{
//            throw new UsernameNotFoundException("invalid user request");
//        }
//        // after we generate the token from AuthRequest we have to check whether the JWT create is authenticated or not
//    }
//}
//
//// Step 4 or 5 now we will have to check the feilds of the validate token . Now we have to add a filter for validating this jwt created filter class after this
