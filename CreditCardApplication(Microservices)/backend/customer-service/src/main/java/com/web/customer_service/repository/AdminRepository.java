package com.web.customer_service.repository;

import com.web.customer_service.model.AdminDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminDetails,Long> {

    AdminDetails findByEmail(String email);

}
