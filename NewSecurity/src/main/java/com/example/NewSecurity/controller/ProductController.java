package com.example.NewSecurity.controller;

import com.example.NewSecurity.dto.Product;
import com.example.NewSecurity.entity.UserInfo;
import com.example.NewSecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome this endpoint is not secure";
    }


    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return productService.addUser(userInfo);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // What this annotation does is that it checks the accessecibilty of the api request by checking authority of the user
    public List<Product> getAllProducts(){
        return  productService.getProducts();
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id){
        return productService.getProduct(id);
    }

}
