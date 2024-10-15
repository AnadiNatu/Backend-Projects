package com.creditcard.system.repository;

import com.creditcard.system.model.AdminDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminDetails,Long> {

    AdminDetails findByEmail(String email);

}
