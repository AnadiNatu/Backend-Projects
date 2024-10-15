package com.pokemon.api.repository;


import com.pokemon.api.model.Pokemon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PokemonRepositoryTest {

    @Autowired
    private PokemonRepository pokemonRepository;


    @Test
    public void pokemonRepository_saveAll_ReturnSavePokemon(){

        //Arrange
        Pokemon pokemon = Pokemon.builder()
                .name("Pikacu")
                .type("Electric")
                .build();

        //Act
        Pokemon savePokemon = pokemonRepository.save(pokemon);

        //Assert
        Assertions.assertThat(savePokemon).isNotNull();
        Assertions.assertThat(savePokemon.getId()).isGreaterThan(0);
    }

    @Test
    public void pokemonRepository_getAll_returnMoreThanOnePokemon(){


        //Arrange
        Pokemon pokemon1 = Pokemon.builder().name("Pikacu").type("Electric").build();
        Pokemon pokemon2 = Pokemon.builder().name("Pikacu1").type("Electric1").build();


        pokemonRepository.save(pokemon1);
        pokemonRepository.save(pokemon2);


        //Act

        List<Pokemon> pokemonList = pokemonRepository.findAll();


        //Assert

        Assertions.assertThat(pokemonList).isNotNull();
        Assertions.assertThat(pokemonList.size()).isEqualTo(2);


    }

    @Test
    public void pokemonRepository_getById_returnPokemonById() {

        Pokemon pokemon1 = Pokemon.builder().name("Pikacu").type("Electric").build();

        pokemonRepository.save(pokemon1);


        //Act
        Pokemon pokemonReturned = pokemonRepository.findById(pokemon1.getId()).get();

        //Assert
        Assertions.assertThat(pokemonReturned).isNotNull();
        Assertions.assertThat(pokemon1.getName()).isEqualTo("Pikacu");

    }



    //Test for a custom query method
    @Test
    public void pokemonRepository_findByType_returnsPokemonByType(){

        //Arrange

        Pokemon pokemon1 = Pokemon.builder().name("Pikacu").type("Electric").build();

        pokemonRepository.save(pokemon1);

        //Act

        Pokemon pokemonReturned = pokemonRepository.findByType(pokemon1.getType()).get();

        //Assert
        Assertions.assertThat(pokemonReturned).isNotNull();
        Assertions.assertThat(pokemonReturned.getType()).isEqualTo("Electric");


    }

    @Test
    public void pokemonRepository_updatePokemon_returnsUpdatedPokemon(){

        //Arrange

        Pokemon pokemon1 = Pokemon.builder().name("Pikacu").type("Electric").build();

        pokemonRepository.save(pokemon1);

        Pokemon savedPokemon = pokemonRepository.findById(pokemon1.getId()).get();

        savedPokemon.setType("Electric");
        savedPokemon.setName("Raichu");

        //Act

        Pokemon pokemonReturned = pokemonRepository.save(savedPokemon);

        //Assert
        Assertions.assertThat(pokemonReturned).isNotNull();
        Assertions.assertThat(pokemonReturned.getType()).isEqualTo("Electric");
        Assertions.assertThat(pokemonReturned.getName()).isEqualTo("Raichu");


    }

    @Test
    public void pokemonRepository_deleteById_returnsNoPokemon(){

        //Arrange

        Pokemon pokemon1 = Pokemon.builder().name("Pikacu").type("Electric").build();

        pokemonRepository.save(pokemon1);

        //Act

        pokemonRepository.deleteById(pokemon1.getId());
        Optional<Pokemon> pokemonReturned = pokemonRepository.findById(pokemon1.getId());

        //Assert
        Assertions.assertThat(pokemonReturned).isEmpty();
    }
}
