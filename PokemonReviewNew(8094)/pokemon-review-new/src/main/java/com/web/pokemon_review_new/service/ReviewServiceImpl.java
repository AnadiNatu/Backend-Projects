package com.web.pokemon_review_new.service;

import com.web.pokemon_review_new.dto.ReviewDto;
import com.web.pokemon_review_new.exceptions.PokemonNotFoundException;
import com.web.pokemon_review_new.exceptions.ReviewNotFoundException;
import com.web.pokemon_review_new.model.Pokemon;
import com.web.pokemon_review_new.model.Review;
import com.web.pokemon_review_new.repository.PokemonRepository;
import com.web.pokemon_review_new.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    PokemonRepository pokemonRepository;


    @Override
    public ReviewDto createReview(Long pokemonId, ReviewDto reviewDto) {

        Review review = new Review();

        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon With Associate Reviews Not Found "));

        review.setPokemon(pokemon);

        Review newReview = reviewRepository.save(review);

        return mapToDto(review);

    }

    @Override
    public List<ReviewDto> getReviewByPokemoneId(Long pokemonId) {

        List<Review> reviews = reviewRepository.findByPokemonId(pokemonId);

        return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(Long reviewId, Long pokemonId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon With Associate Reviews Not Found "));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with Associate Pokemon Not Found"));
        //orElseThrow() can be used by the error throwing function

        if (review.getPokemon().getId() != pokemon.getId()){

            throw new ReviewNotFoundException("This Review Is Not related to any Pokemon");
        }

        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(Long pokemonId, Long reviewId, ReviewDto reviewDto) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon With Associate Reviews Not Found"));

        Review review= reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Pokemon With Associate Reviews Not Found "));

        if (review.getPokemon().getId() != pokemon.getId()){

            throw new ReviewNotFoundException("This Review Is Not related to any Pokemon");
        }

        review.setTitle(reviewDto.getTitle());
        review.setStars(reviewDto.getStars());
        review.setContent(reviewDto.getContent());

        Review updateReview = reviewRepository.save(review);

        return mapToDto(updateReview);



    }

    @Override
    public void deleteReview(Long pokemonId, Long reviewId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon With Associate Reviews Not Found"));

        Review review= reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Pokemon With Associate Reviews Not Found "));

        if (review.getPokemon().getId() != pokemon.getId()){

            throw new ReviewNotFoundException("This Review Is Not related to any Pokemon");
        }

        reviewRepository.delete(review);

    }

    private ReviewDto mapToDto(Review review){

        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(reviewDto.getId());
        reviewDto.setTitle(reviewDto.getTitle());
        reviewDto.setContent(reviewDto.getContent());
        reviewDto.setStars(reviewDto.getStars());

        return reviewDto;

    }

    private Review mapToEntity(ReviewDto reviewDto){

        Review review = new Review();

        review.setId(review.getId());
        review.setTitle(review.getTitle());
        review.setContent(review.getContent());
        review.setStars(review.getStars());

        return review;
    }
}
