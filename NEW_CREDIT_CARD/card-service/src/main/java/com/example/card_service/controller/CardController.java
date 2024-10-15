package com.example.card_service.controller;


import com.example.admin_service.dtos.CardCreateRequest;
import com.example.admin_service.dtos.CardResponseDto;
import com.example.admin_service.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {


    @Autowired
    private CardService cardService;

    @PostMapping("/generate")
    public ResponseEntity<CardResponseDto> generateCard(@RequestBody CardCreateRequest cardCreateRequest) throws IllegalAccessException {

        return new ResponseEntity<>(cardService.generateCard(cardCreateRequest), HttpStatus.CREATED);

    }


    @GetMapping("/{customerId}")
    public ResponseEntity<List<CardResponseDto>> getCardByCustomerId(@PathVariable Long customerId){

        return new ResponseEntity<>(cardService.getCardsByCustomerId(customerId) , HttpStatus.OK);

    }

    @GetMapping("/{customerId}/{cardType}")
    public ResponseEntity<List<CardResponseDto>> getCardByCustomerIdAndCardType(@PathVariable Long customerId , @PathVariable String cardType){

        return new ResponseEntity<>(cardService.getCardsByCustomerIdAndCardType(customerId, cardType) , HttpStatus.OK);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<CardResponseDto>> getAllCards(){

        return new ResponseEntity<>(cardService.getAllCards() , HttpStatus.OK);

    }

}
