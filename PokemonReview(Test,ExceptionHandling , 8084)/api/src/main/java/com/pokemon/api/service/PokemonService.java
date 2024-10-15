package com.pokemon.api.service;

import com.pokemon.api.dto.PokemonDto;
import com.pokemon.api.dto.PokemonResponse;
import com.pokemon.api.model.Pokemon;

import java.util.List;

public interface PokemonService {

    PokemonDto createPokemon(PokemonDto pokemonDto);

    PokemonResponse getAllPokemon(int pageNo, int pageSize);

    PokemonDto getPokemonById(Long id);

    PokemonDto updatePokemon(PokemonDto pokemonDto , Long id);

    void deletePokemonById(Long id);
}
