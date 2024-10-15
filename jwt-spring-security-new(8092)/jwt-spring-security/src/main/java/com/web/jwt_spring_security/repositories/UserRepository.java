package com.web.jwt_spring_security.repositories;



import com.web.jwt_spring_security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User findFirstByEmail(String email);
}
