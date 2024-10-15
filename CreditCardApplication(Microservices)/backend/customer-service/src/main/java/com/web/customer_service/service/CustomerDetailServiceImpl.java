package com.web.customer_service.service;


import com.web.customer_service.model.CustomerDetails;
import com.web.customer_service.repository.CustomerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService{

    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;

    @Override
    public CustomerDetails addCustomer(CustomerDetails customerDetails) {
        return customerDetailsRepository.save(customerDetails);
    }

    @Override
    public List<CustomerDetails> getAllCustomer() {
        return customerDetailsRepository.findAll();
    }

    @Override
    public Optional<CustomerDetails> getCustomerById(Long id) {
        return customerDetailsRepository.findById(id);
    }

    @Override
    public CustomerDetails updateCustomerDetails(Long id, Optional<CustomerDetails> customerDetails) {
        if (customerDetailsRepository.existsById(id)) {
            Optional<CustomerDetails> updatedCustomer = Optional.of(customerDetails.get());
            customerDetailsRepository.save(updatedCustomer);

            return updatedCustomer;
        }
        return null;

    }

    @Override
    public void deleteAllCustomer() {

        customerDetailsRepository.deleteAll();
    }

    @Override
    public void deleteCustomerById(Long id) {

        customerDetailsRepository.deleteById(id);

    }
}
