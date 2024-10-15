package com.example.system.mapper;

import com.example.system.dto.CreditCardDto;
import com.example.system.dto.CustomerDto;
import com.example.system.model.CreditCardEntity;

import java.time.LocalDate;

public interface CreditCardMapper {

    CreditCardDto mapToCreditCard(CustomerDto customerDto) throws IllegalAccessException;
    String mapBankNameToCardNumberPrefix(String bankName) throws IllegalAccessException;
    int mapBankNameToCvvRangeStart(String bankName) throws IllegalAccessException;
    int mapBankNameToCvvRangeEnd(String bankName) throws IllegalAccessException;
    LocalDate calculateExpiryDate();
    CreditCardEntity mapToCreditCardEntity(CreditCardDto creditCardDto);



}
