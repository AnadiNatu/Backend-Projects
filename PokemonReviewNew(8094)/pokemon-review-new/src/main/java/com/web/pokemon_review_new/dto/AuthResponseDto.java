package com.web.pokemon_review_new.dto;

import lombok.Data;

@Data
public class AuthResponseDto {

    private String accessToken;
    private String tokenType = "Bearer";

    public AuthResponseDto(String token) {
        this.accessToken = accessToken;
    }

}
