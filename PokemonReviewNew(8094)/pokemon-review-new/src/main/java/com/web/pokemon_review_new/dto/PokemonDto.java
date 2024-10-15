package com.web.pokemon_review_new.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PokemonDto {

    private Long id;
    private String name;
    private String type;
}
