package com.web.pokemon_review_new.service;

import com.web.pokemon_review_new.dto.PokemonDto;
import com.web.pokemon_review_new.dto.PokemonResponse;

public interface PokemonService {

    PokemonDto createPokemon(PokemonDto pokemonDto);

    PokemonResponse getAllPokemon(int pageNo, int pageSize);

    PokemonDto getPokemonById(Long id);

    PokemonDto updatePokemon(PokemonDto pokemonDto , Long id);

    void deletePokemonById(Long id);

}
