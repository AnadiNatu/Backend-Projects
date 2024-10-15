package com.creditcard.system.repository;

import com.creditcard.system.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails,Long> {

    CustomerDetails findByEmail(String email);
}
