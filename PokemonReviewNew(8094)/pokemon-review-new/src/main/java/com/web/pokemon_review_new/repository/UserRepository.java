package com.web.pokemon_review_new.repository;

import com.web.pokemon_review_new.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

}
