package com.web.customer_service.repository;

import com.web.customer_service.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails,Long> {

    CustomerDetails findByEmail(String email);
}
