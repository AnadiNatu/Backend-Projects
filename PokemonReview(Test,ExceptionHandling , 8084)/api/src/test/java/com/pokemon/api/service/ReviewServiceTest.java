package com.pokemon.api.service;


import com.pokemon.api.dto.PokemonDto;
import com.pokemon.api.dto.ReviewDto;
import com.pokemon.api.model.Pokemon;
import com.pokemon.api.model.Review;
import com.pokemon.api.repository.PokemonRepository;
import com.pokemon.api.repository.ReviewRepository;
import com.pokemon.api.service.impl.ReviewServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {


    @Mock
    PokemonRepository pokemonRepository;

    @Mock
    ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Pokemon pokemon;
    private Review review;
    private ReviewDto reviewDto;
    private PokemonDto pokemonDto;


    // This annotation makes the method below it execute before the execution of each of the test methods
    @BeforeEach
    public void init(){

        Pokemon pokemon = Pokemon.builder().name("Pikacu").type("Electric").build();
        PokemonDto pokemonDto = PokemonDto.builder().name("PikacuDto").type("ElectricDto").build();
        Review review = Review.builder().title("title").content("content").stars(5).build();
        ReviewDto reviewDto = ReviewDto.builder().title("titleDto").content("contentDto").stars(5).build();
    }

    @Test
    public void reviewService_createReview_returnsReviewDto(){

        when(pokemonRepository.findById(pokemon.getId())).thenReturn(Optional.ofNullable(pokemon));

        when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(review); //this makes a fake Review Object

        ReviewDto saveReview = reviewService.createReview(pokemon.getId(),reviewDto);

        Assertions.assertThat(saveReview).isNotNull();


    }


    @Test
    public void reviewService_getReviewByPokemonId_returnReviewDto(){

        Long reviewId = 1L;

        review.setPokemon(pokemon);

        when(reviewRepository.findByPokemonId(reviewId)).thenReturn(Arrays.asList(review));

        List<ReviewDto> pokemonReturn = reviewService.getReviewByPokemoneId(reviewId);

        Assertions.assertThat(pokemonReturn).isNotNull();
    }


    @Test
    public void reviewService_getReviewById_returnReviewDto(){

        Long reviewId = 1L;
        Long pokemonId = 1L;

        review.setPokemon(pokemon);

        when(reviewRepository.findByPokemonId(reviewId)).thenReturn(Arrays.asList(review));
        when(pokemonRepository.findById(pokemon.getId())).thenReturn(Optional.ofNullable(pokemon));

        ReviewDto reviewReturn = reviewService.getReviewById(reviewId,pokemonId);

        Assertions.assertThat(reviewReturn).isNotNull();


    }


    @Test
    public void reviewService_updateReview_returnReviewDto(){

        Long reviewId = 1L;
        Long pokemonId = 1L;

        pokemon.setReviews(Arrays.asList(review));
        review.setPokemon(pokemon);

        when(reviewRepository.findByPokemonId(reviewId)).thenReturn(Arrays.asList(review));
        when(pokemonRepository.findById(pokemon.getId())).thenReturn(Optional.ofNullable(pokemon));
        when(reviewRepository.save(review)).thenReturn(review);

        ReviewDto updateReview = reviewService.updateReview(pokemonId,reviewId,reviewDto);


        Assertions.assertThat(updateReview).isNotNull();

    }


    @Test
    public void reviewService_deleteReview_returnVoid(){

        Long reviewId = 1L;
        Long pokemonId = 1L;

        pokemon.setReviews(Arrays.asList(review));
        review.setPokemon(pokemon);

        when(reviewRepository.findByPokemonId(reviewId)).thenReturn(Arrays.asList(review));
        when(pokemonRepository.findById(pokemon.getId())).thenReturn(Optional.ofNullable(pokemon));

        assertAll(() -> reviewService.deleteReview(pokemonId,reviewId));

    }

}
