package com.example.system.service;

import com.example.system.dto.CreditCardDto;
import com.example.system.dto.CustomerDto;
import com.example.system.model.CreditCardEntity;

import java.util.List;

public interface CreditCardService {

    CreditCardDto generateCreditCard(CustomerDto customerDto) throws IllegalAccessException;

    CreditCardEntity getCreditCardDetailsByName(String name);

    List<CreditCardEntity> getAllCards();

}
