package com.web.customer_service.service;


import com.web.customer_service.model.CustomerDetails;

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
