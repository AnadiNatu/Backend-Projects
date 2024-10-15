package com.example.Task_SpringBoot.repository;

import com.example.Task_SpringBoot.entities.Users;
import com.example.Task_SpringBoot.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

   Optional<Users> findFirstByEmail(String username);

   Optional<Users> findByUserRole(UserRole userRole);
}
