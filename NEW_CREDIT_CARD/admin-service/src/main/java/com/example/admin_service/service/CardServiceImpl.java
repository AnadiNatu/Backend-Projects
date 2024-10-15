package com.example.admin_service.service;

import com.example.admin_service.dtos.CardCreateRequest;
import com.example.admin_service.dtos.CardResponseDto;
import com.example.admin_service.mappers.CardMapper;
import com.example.admin_service.model.CardDetails;
import com.example.admin_service.model.CustomerDetail;
import com.example.admin_service.repository.CardRepository;
import com.example.admin_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService{


    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private CardRepository cardRepository;

    @Override
    public CardResponseDto generateCard(CardCreateRequest cardCreateRequest) throws IllegalAccessException {

        CustomerDetail customer = customerRepository.findById(cardCreateRequest.getCustomerId()).orElseThrow(() -> new IllegalArgumentException("Invalid Customer Id"));

        CardResponseDto responseDto = cardMapper.mapToDto(cardCreateRequest);

        CardDetails card = cardMapper.mapToEntity(responseDto);

        card.setCustomerDetail(customer);

        cardRepository.save(card);

        return responseDto;

    }



    @Override
    public List<CardResponseDto> getCardsByCustomerId(Long customerId){

        CustomerDetail customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Invalid Customer Id"));

        List<CardDetails> cards = cardRepository.findAll().stream()
                .filter(card -> card.getCustomerDetail().getId().equals(customerId))
                .collect(Collectors.toList());

        return cards.stream()
                .map(cardMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CardResponseDto> getCardsByCustomerIdAndCardType(Long customerId, String cardType) {

        CustomerDetail customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Invalid Customer Id"));

        List<CardDetails> cards = cardRepository.findAll().stream()
                .filter(card -> card.getCardType().equals(cardType) && card.getCustomerDetail().getId().equals(customerId))
                .collect(Collectors.toList());

        return cards.stream()
                .map(cardMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CardResponseDto> getAllCards(){

        return cardRepository.findAll().stream().map(cardMapper::mapToResponse).collect(Collectors.toList());

    }

}
