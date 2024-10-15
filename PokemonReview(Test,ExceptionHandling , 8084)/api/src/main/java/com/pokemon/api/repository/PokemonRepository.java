package com.pokemon.api.repository;

import com.pokemon.api.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PokemonRepository extends JpaRepository<Pokemon , Long> {


    Optional<Pokemon> findByType(String type);

}
