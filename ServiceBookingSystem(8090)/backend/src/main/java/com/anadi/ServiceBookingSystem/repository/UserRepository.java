package com.anadi.ServiceBookingSystem.repository;

import com.anadi.ServiceBookingSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {
   User findFirstByEmail(String email);
}
