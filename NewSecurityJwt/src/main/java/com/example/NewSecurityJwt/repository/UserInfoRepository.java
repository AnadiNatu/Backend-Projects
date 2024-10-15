package com.example.NewSecurityJwt.repository;

import com.example.NewSecurityJwt.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {

    UserInfo findByUsername(String username);
}
