package com.example.system.repository;

import com.example.system.model.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity , Long> {

    CreditCardEntity findByCustomerName(String name);
}
