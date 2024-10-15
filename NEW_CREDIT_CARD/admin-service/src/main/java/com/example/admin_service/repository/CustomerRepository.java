package com.example.admin_service.repository;

import com.example.admin_service.model.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetail,Long> {

    Optional<CustomerDetail> findByEmail(String email);
}
