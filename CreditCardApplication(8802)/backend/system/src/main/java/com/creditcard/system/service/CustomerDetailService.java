package com.creditcard.system.service;

import com.creditcard.system.model.CustomerDetails;

import java.util.List;
import java.util.Optional;

public interface CustomerDetailService {

    CustomerDetails addCustomer(CustomerDetails customerDetails);
    List<CustomerDetails> getAllCustomer();
    Optional<CustomerDetails> getCustomerById(Long id);
    void deleteAllCustomer();
    void deleteCustomerById(Long id);

    CustomerDetails updateCustomerDetails(Long id, Optional<CustomerDetails> customer);
}
