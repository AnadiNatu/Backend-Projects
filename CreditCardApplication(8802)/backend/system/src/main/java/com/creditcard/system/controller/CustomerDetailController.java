package com.creditcard.system.controller;


import com.creditcard.system.model.CustomerDetails;
import com.creditcard.system.repository.CustomerDetailsRepository;
import com.creditcard.system.service.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerDetailController {


    @Autowired
    CustomerDetailService customerDetailService;

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDetails>> getAllCustomers(){
        List<CustomerDetails> customerList = customerDetailService.getAllCustomer();

        return ResponseEntity.ok(customerList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<CustomerDetails>> getCustomerById(@PathVariable Long id){
        Optional<CustomerDetails> customer = customerDetailService.getCustomerById(id);

        return ResponseEntity.ok(customer);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<CustomerDetails>> updateCustomer(@PathVariable Long id, @RequestBody CustomerDetails customerDetails){

        Optional<CustomerDetails> customer = customerDetailService.getCustomerById(id);

        if(customer.isPresent()){
            customerDetailService.updateCustomerDetails(id,customer);
        }

        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAllCustomer(){

        customerDetailService.deleteAllCustomer();

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAllCustomer(@PathVariable Long id){

        customerDetailService.deleteCustomerById(id);

        return ResponseEntity.noContent().build();
    }
}
