package com.pokemon.api.service;

import com.pokemon.api.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(Long pokemonId , ReviewDto reviewDto);

    List<ReviewDto> getReviewByPokemoneId(Long id);

    ReviewDto getReviewById (Long reviewId , Long pokemonId);

    ReviewDto updateReview(Long pokemonId , Long reviewId , ReviewDto reviewDto);

    void deleteReview(Long  pokemonId , Long reviewId);

}
