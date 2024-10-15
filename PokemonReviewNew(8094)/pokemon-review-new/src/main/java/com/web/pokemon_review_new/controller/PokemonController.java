package com.web.pokemon_review_new.controller;

import com.web.pokemon_review_new.dto.PokemonDto;
import com.web.pokemon_review_new.dto.PokemonResponse;
import com.web.pokemon_review_new.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;


    @GetMapping("pokemon")
    public ResponseEntity<PokemonResponse> getPokemon(
            @RequestParam(value = "pageNo" , defaultValue = "0" , required = false) int pageNo,
            @RequestParam(value = "pageSize" , defaultValue = "10" , required = false) int pageSize){

        return new ResponseEntity<>(pokemonService.getAllPokemon(pageNo,pageSize), HttpStatus.OK);
    }


    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDto> getPokemonById(@PathVariable Long id){

        return ResponseEntity.ok(pokemonService.getPokemonById(id));

    }

    @PostMapping("pokemon/create")
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }


    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable("id") Long id){

        PokemonDto response = pokemonService.updatePokemon(pokemonDto,id);

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") Long id){

        pokemonService.deletePokemonById(id);

        return new ResponseEntity<>("Pokemon Deleted", HttpStatus.OK);
    }


}
