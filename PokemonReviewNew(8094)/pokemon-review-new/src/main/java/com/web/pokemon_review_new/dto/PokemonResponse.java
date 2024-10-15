package com.web.pokemon_review_new.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonResponse {

    private List<PokemonDto> content;
    private int pageNo;
    private  int pageSize;
    private Long TotalElement;
    private int  totalPages;
    private boolean last;

}
