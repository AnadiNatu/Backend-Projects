package com.web.pokemon_review_new.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.pokemon_review_new.dto.PokemonDto;
import com.web.pokemon_review_new.dto.PokemonResponse;
import com.web.pokemon_review_new.dto.ReviewDto;
import com.web.pokemon_review_new.model.Pokemon;
import com.web.pokemon_review_new.model.Review;
import com.web.pokemon_review_new.service.PokemonService;
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
import org.springframework.security.core.parameters.P;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@WebMvcTest(controllers = PokemonController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonService pokemonService;

    @Autowired
    private ObjectMapper objectMapper;

    private Pokemon pokemon;

    private Review review;

    private ReviewDto reviewDto;

    private PokemonDto pokemonDto;


    @BeforeEach
    public void init(){

        Pokemon pokemon = Pokemon.builder().name("Pikacu").type("Electric").build();
        PokemonDto pokemonDto = PokemonDto.builder().name("PikacuDto").type("ElectricDto").build();
        Review review = Review.builder().title("title").content("content").stars(5).build();
        ReviewDto reviewDto = ReviewDto.builder().title("titleDto").content("contentDto").stars(5).build();
    }


    @Test
    public void pokemonController_createPokemon_returnsCreatedPokemon() throws Exception{

        given(pokemonService.createPokemon(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("api/pokemon/create")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(objectMapper.writeValueAsString(pokemonDto)));


        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(pokemonDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", CoreMatchers.is(pokemonDto.getType())))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void pokemonController_getAllPokemon_returnResponseDto() throws Exception {

        PokemonResponse responseDto = PokemonResponse.builder().pageSize(10).pageNo(1).last(true).content(Arrays.asList(pokemonDto)).build();

        when(pokemonService.getAllPokemon(1,10)).thenReturn(responseDto);

        ResultActions response = mockMvc.perform(get("/api/pokemon")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNo","1")
                .param("pageSize" , "10"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", CoreMatchers.is(responseDto.getContent().size())));
    }


    @Test
    public void pokemonController_getPokemonById_returnPokemonDto() throws Exception {

        Long pokemonId = 1L;
        when(pokemonService.getPokemonById(pokemonId)).thenReturn(pokemonDto);

        ResultActions response = mockMvc.perform(get("/api/pokemon/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pokemonDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name" , CoreMatchers.is(pokemonDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type" , CoreMatchers.is(pokemonDto.getType())));


    }

    @Test
    public void pokemonController_updatePokemon_returnPokemonDto() throws Exception {

        Long pokemonId = 1L;
        when(pokemonService.updatePokemon(pokemonDto,pokemonId)).thenReturn(pokemonDto);

        ResultActions response = mockMvc.perform(put("/api/pokemon/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pokemonDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name" , CoreMatchers.is(pokemonDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type" , CoreMatchers.is(pokemonDto.getType())));


    }


    @Test
    public void pokemonController_deletePokemonById_returnString() throws Exception {

        Long pokemonId = 1L;
        doNothing().when(pokemonService).deletePokemonById(pokemonId);

        ResultActions response = mockMvc.perform(delete("/api/pokemon/1/delete")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
