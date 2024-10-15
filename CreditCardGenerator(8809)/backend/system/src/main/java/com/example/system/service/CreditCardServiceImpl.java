package com.example.system.service;


import com.example.system.dto.CreditCardDto;
import com.example.system.dto.CustomerDto;
import com.example.system.mapper.CreditCardMapper;
import com.example.system.model.CreditCardEntity;
import com.example.system.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService{

    @Autowired
    private CreditCardMapper creditCardMapper;

    @Autowired
    private CreditCardRepository creditCardRepository;


    @Override
    public CreditCardDto generateCreditCard(CustomerDto customerDto) throws IllegalAccessException {

        CreditCardDto creditCardDto = creditCardMapper.mapToCreditCard(customerDto);

        CreditCardEntity creditCard = creditCardMapper.mapToCreditCardEntity(creditCardDto);

        creditCardRepository.save(creditCard);

        return creditCardDto;

    }

    @Override
    public CreditCardEntity getCreditCardDetailsByName(String name) {

        CreditCardEntity creditCard = creditCardRepository.findByCustomerName(name);

        if (creditCard.getCustomerName().matches(name)){
            return  creditCard;
        }else
            return null;
    }

    @Override
    public List<CreditCardEntity> getAllCards() {

        List<CreditCardEntity> creditCardList = creditCardRepository.findAll();

        if (creditCardList.isEmpty())
            return null;
        else
            return creditCardList;
    }

}
