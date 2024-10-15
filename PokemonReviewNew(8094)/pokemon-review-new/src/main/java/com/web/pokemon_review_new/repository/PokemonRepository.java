package com.web.pokemon_review_new.repository;

import com.web.pokemon_review_new.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon,Long> {

    Optional<Pokemon> findByType(String type);
}
