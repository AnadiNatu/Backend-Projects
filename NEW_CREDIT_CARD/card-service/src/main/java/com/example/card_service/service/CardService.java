package com.example.card_service.service;

import com.example.admin_service.dtos.CardCreateRequest;
import com.example.admin_service.dtos.CardResponseDto;

import java.util.List;

public interface CardService {

    public CardResponseDto generateCard(CardCreateRequest cardCreateRequest) throws IllegalAccessException;

    public List<CardResponseDto> getCardsByCustomerId(Long customerId);

    public List<CardResponseDto> getCardsByCustomerIdAndCardType(Long customerId , String cardType);

    public List<CardResponseDto> getAllCards();

}
