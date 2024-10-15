package com.example.system.controller;

import com.example.system.dto.CreditCardDto;
import com.example.system.dto.CustomerDto;
import com.example.system.model.CreditCardEntity;
import com.example.system.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/generateCreditCard")
    public ResponseEntity<CreditCardDto> generateCreditCard(@RequestBody CustomerDto customerDto) throws IllegalAccessException {

        CreditCardDto creditCardDto = creditCardService.generateCreditCard(customerDto);

        return ResponseEntity.ok(creditCardDto);
    }

    @GetMapping("/getCardDetails/{name}")
    public ResponseEntity<CreditCardEntity> getCardDetails(@PathVariable String name){

        CreditCardEntity creditCard = creditCardService.getCreditCardDetailsByName(name);

        return ResponseEntity.ok(creditCard);

    }

    @GetMapping("/getAllCards")
    public ResponseEntity<List<CreditCardEntity>> getAllCards(){

        List<CreditCardEntity> creditCardList = creditCardService.getAllCards();

        return ResponseEntity.ok(creditCardList);
     }
}
