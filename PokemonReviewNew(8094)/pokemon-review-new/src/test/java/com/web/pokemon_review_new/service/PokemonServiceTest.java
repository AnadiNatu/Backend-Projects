package com.web.pokemon_review_new.service;

import com.web.pokemon_review_new.dto.PokemonDto;
import com.web.pokemon_review_new.dto.PokemonResponse;
import com.web.pokemon_review_new.model.Pokemon;
import com.web.pokemon_review_new.repository.PokemonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

//This annotation helps in setting up the mock environment by bring in the Mockito class nessacary
@ExtendWith(MockitoExtension.class)
public class PokemonServiceTest {


    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Test
    public void pokemonService_createPokemon_returnPokemonDto(){

        //Arrange
        Pokemon pokemon1 = Pokemon.builder().name("Pikacu").type("Electric").build();

        PokemonDto pokemonDto = PokemonDto.builder().name("Pikacu").type("Electric").build();

        //Act

        //This when() method will mock any object  or function . This method creates the mock environment , in which pass and return any object or function
        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon1);

        PokemonDto savePokemon = pokemonService.createPokemon(pokemonDto);

        //Assert
        Assertions.assertThat(savePokemon).isNotNull();


    }


    @Test
    public void pokemonService_getAllPokemon_returnAllPokemon(){

        Page<Pokemon> pokemons = Mockito.mock(Page.class);

        when(pokemonRepository.findAll(Mockito.mock(Pageable.class))).thenReturn(pokemons);

        PokemonResponse savePokemon = pokemonService.getAllPokemon(1 , 10);

        Assertions.assertThat(savePokemon).isNotNull();

    }

    @Test
    public void pokemonService_findPokemonById_returnPokemonDtoById(){

        //Arrange
        Pokemon pokemon1 = Pokemon.builder().name("Pikacu").type("Electric").build();

        when(pokemonRepository.findById(pokemon1.getId())).thenReturn(Optional.ofNullable(pokemon1));

        //Act
        PokemonDto savePokemon = pokemonService.getPokemonById(pokemon1.getId());

        //Assert
        Assertions.assertThat(savePokemon).isNotNull();

    }

    public void pokemonService_updatePokemon_returnPokemonDto(){

        Pokemon pokemon = Pokemon.builder().name("Pikacu").type("Electric").build();
        PokemonDto pokemonDto = PokemonDto.builder().name("Pikacu").type("Electric").build();

        when(pokemonRepository.findById(pokemon.getId())).thenReturn(Optional.ofNullable(pokemon));
        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon);

        PokemonDto savedPokemon = pokemonService.updatePokemon(pokemonDto , pokemonDto.getId());

        Assertions.assertThat(savedPokemon).isNotNull();


    }

    @Test
    public void pokemonService_deletePokemonById_returnsNoPokemon(){

        Pokemon pokemon = Pokemon.builder().name("Pikacu").type("Electric").build();

        when(pokemonRepository.findById(pokemon.getId())).thenReturn(Optional.ofNullable(pokemon));

        assertAll(() -> pokemonService.deletePokemonById(pokemon.getId()));
    }
}
