package com.pokemon.api.dto;



import lombok.Builder;
import lombok.Data;


@Data
public class AuthResponseDto {

    private String accessToken;
    private String tokenType = "Bearer";

    public AuthResponseDto(String token) {
        this.accessToken = accessToken;
    }
}
