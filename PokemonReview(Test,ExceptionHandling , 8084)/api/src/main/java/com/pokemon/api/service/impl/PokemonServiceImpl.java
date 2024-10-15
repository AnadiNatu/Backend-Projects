package com.pokemon.api.service.impl;

import com.pokemon.api.dto.PokemonDto;
import com.pokemon.api.dto.PokemonResponse;
import com.pokemon.api.exceptions.PokemonNotFoundException;
import com.pokemon.api.model.Pokemon;
import com.pokemon.api.repository.PokemonRepository;
import com.pokemon.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    PokemonRepository pokemonRepository;

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {

        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());


        Pokemon newPokemon = pokemonRepository.save(pokemon);

        PokemonDto pokemonResponse = new PokemonDto();
        pokemonResponse.setId(newPokemon.getId());
        pokemonResponse.setName(newPokemon.getName());
        pokemonResponse.setType(newPokemon.getType());

        return pokemonResponse;


        //We are using purely dtos from the start to interact with th request coming to the api
    }

    @Override
    public PokemonResponse getAllPokemon(int pageNo , int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);

        List<Pokemon> listOfPokemons = pokemons.getContent();

        List<PokemonDto> content =  listOfPokemons.stream().map(p -> mapToDto(p) ).collect(Collectors.toList());

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(pokemons.getNumber());
        pokemonResponse.setPageSize(pokemonResponse.getPageSize());
        pokemonResponse.setTotalElement(pokemons.getTotalElements());
        pokemonResponse.setTotalPages(pokemonResponse.getTotalPages());
        pokemonResponse.setLast(pokemons.isLast());

        return pokemonResponse;

    }



    @Override
    public PokemonDto getPokemonById(Long id) {

        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon Could Not Be Found"));

        return mapToDto(pokemon);
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, Long id) {

        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon Can't Be Found"));

        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemon.getType());

        Pokemon updatePokemon = pokemonRepository.save(pokemon);

        return mapToDto(updatePokemon);

    }

    @Override
    public void deletePokemonById(Long id) {

        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be deleted"));

        pokemonRepository.delete(pokemon);


    }

    private PokemonDto mapToDto(Pokemon pokemon){

        PokemonDto pokemonDto = new PokemonDto();

        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());

        return pokemonDto;

    }

    private Pokemon mapToEntity(PokemonDto pokemonDto){

        Pokemon pokemon = new Pokemon();

        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        return pokemon;
    }



    //it is always a good practice to use dtos to interact with the end point
    //making changes to the entity directly will hamper the sanity of our entity


}
