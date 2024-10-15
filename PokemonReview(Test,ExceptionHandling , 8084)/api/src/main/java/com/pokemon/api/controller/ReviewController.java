package com.pokemon.api.controller;


import com.pokemon.api.dto.ReviewDto;
import com.pokemon.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @PostMapping("/pokemon/{pokemonId}/review")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "pokemonId") Long pokemonId , @RequestBody ReviewDto reviewDto){

        return new ResponseEntity<>(reviewService.createReview(pokemonId, reviewDto), HttpStatus.CREATED);

    }

    @GetMapping("/pokemon/{pokemonId}/allReview")
    public ResponseEntity<List<ReviewDto>> getReviewsByPokemonId(@PathVariable(value = "pokemonId") Long pokemonId){

        return new ResponseEntity<>(reviewService.getReviewByPokemoneId(pokemonId), HttpStatus.ACCEPTED);

    }

    @GetMapping("/pokemon/{pokemonId}/review/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "pokemonId") Long pokemonId , @PathVariable(value = "id") Long id){

        return new ResponseEntity<>(reviewService.getReviewById(pokemonId, id),HttpStatus.OK);

    }

    @PutMapping("/pokemon/{pokemonId}/review/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "pokemonId") Long pokemonId , @PathVariable(value = "id") Long id , @RequestBody ReviewDto reviewDto){

        return new ResponseEntity<>(reviewService.updateReview(pokemonId, id, reviewDto),HttpStatus.CREATED);

    }

    @DeleteMapping("/pokemon/{pokemonId}/review/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "pokemonId") Long pokemonId , @PathVariable(value = "id") Long id){

        reviewService.deleteReview(pokemonId, id);

        return new ResponseEntity<>("Review Deteled" , HttpStatus.OK);

    }


    }
