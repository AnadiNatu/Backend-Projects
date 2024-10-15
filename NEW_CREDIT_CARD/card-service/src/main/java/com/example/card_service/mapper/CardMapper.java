package com.example.card_service.mapper;

import com.example.admin_service.dtos.CardCreateRequest;
import com.example.admin_service.dtos.CardResponseDto;
import com.example.admin_service.model.CardDetails;

public interface CardMapper {

    public CardResponseDto mapToDto(CardCreateRequest createRequest) throws IllegalAccessException;

    public CardDetails mapToEntity(CardResponseDto cardResponseDto);

    public CardResponseDto mapToResponse(CardDetails cardDetails);
}
