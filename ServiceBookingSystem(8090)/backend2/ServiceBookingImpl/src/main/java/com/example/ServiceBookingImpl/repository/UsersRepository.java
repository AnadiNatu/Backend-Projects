package com.example.ServiceBookingImpl.repository;

import com.example.ServiceBookingImpl.entity.Users;
import com.example.ServiceBookingImpl.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {


    Optional<Users> findByRole(UserRole role);

    Optional<Users> findByUsername(String username);
}
