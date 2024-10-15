package com.pokemon.api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.api.dto.PokemonDto;
import com.pokemon.api.dto.PokemonResponse;
import com.pokemon.api.dto.ReviewDto;
import com.pokemon.api.model.Pokemon;
import com.pokemon.api.model.Review;
import com.pokemon.api.service.PokemonService;
import com.pokemon.api.service.ReviewService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(controllers = ReviewController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private ObjectMapper objectMapper; //


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
    public void reviewController_getReviewByPokemonId_returnReviewDto() throws Exception{

        Long pokemonId = 1L;
        when(reviewService.getReviewByPokemoneId(pokemonId)).thenReturn(Arrays.asList(reviewDto));

        ResultActions response = mockMvc.perform(get("/api/pokemon/1/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pokemonDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("&.size()" , CoreMatchers.is(Arrays.asList(reviewDto).size())));


    }

    @Test
    public void pokemonController_updateReview_returnReviewDto() throws Exception {

        Long pokemonId = 1L;
        Long reviewId = 1L;
        when(reviewService.updateReview(pokemonId,reviewId,reviewDto)).thenReturn(reviewDto);

        ResultActions response = mockMvc.perform(put("/api/pokemon/1/review/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title" , CoreMatchers.is(reviewDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content" , CoreMatchers.is(reviewDto.getContent())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.star" , CoreMatchers.is(reviewDto.getStars())));

    }

    @Test
    public void reviewController_createReview_returnReviewDto() throws Exception{

        Long pokemonId = 1L;

        when(reviewService.createReview(pokemonId,reviewDto)).thenReturn(reviewDto);

        ResultActions response = mockMvc.perform(post("api/pokemon/1/review")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(objectMapper.writeValueAsString(reviewDto)));


        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title" , CoreMatchers.is(reviewDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content" , CoreMatchers.is(reviewDto.getContent())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.star" , CoreMatchers.is(reviewDto.getStars())));

    }

    @Test
    public void reviewController_getReviewById_returnReviewDto() throws Exception{

        Long pokemonId = 1L;
        Long reviewId = 1L;
        when(reviewService.getReviewById(reviewId,pokemonId)).thenReturn(reviewDto);

        ResultActions response = mockMvc.perform(get("/api/pokemon/1/review/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title" , CoreMatchers.is(reviewDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content" , CoreMatchers.is(reviewDto.getContent())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.star" , CoreMatchers.is(reviewDto.getStars())));


    }

    @Test
    public void reviewController_deleteReviewById_returnString() throws Exception{

        Long pokemonId = 1L;
        Long reviewId = 1L;

        doNothing().when(reviewService).deleteReview(pokemonId,reviewId);

        ResultActions response = mockMvc.perform(delete("/api/pokemon/1/review/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }
}
