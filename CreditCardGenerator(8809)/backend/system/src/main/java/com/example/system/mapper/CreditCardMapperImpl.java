package com.example.system.mapper;

import com.example.system.dto.CreditCardDto;
import com.example.system.dto.CustomerDto;
import com.example.system.model.CreditCardEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;


@Component
public class CreditCardMapperImpl implements CreditCardMapper{

    @Override
    public CreditCardDto mapToCreditCard(CustomerDto customerDto) throws IllegalAccessException {

        CreditCardDto creditCardDto = new CreditCardDto();

        String bankName = customerDto.getName();

        String cardPrefix = mapBankNameToCardNumberPrefix(bankName);
        creditCardDto.setCardNumber(cardPrefix+generateRandomDigits(12));

        LocalDate cardExpiry = calculateExpiryDate();
        creditCardDto.setExpiryDate(cardExpiry);

        int cvvStart = mapBankNameToCvvRangeStart(bankName);
        int cvvEnd = mapBankNameToCvvRangeEnd(bankName);
        creditCardDto.setCvv(generateRandomNumber(cvvStart,cvvEnd));

        creditCardDto.setName(customerDto.getName());
        creditCardDto.setBankName(customerDto.getBankName());

        return creditCardDto;
    }


    private int generateRandomNumber(int cvvStart , int cvvEnd){
        Random random = new Random();

        return random.nextInt(cvvEnd-cvvStart+1)+cvvStart;

    }

    private String generateRandomDigits(int length){

        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < length ; i++) {
        sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    @Override
    public String mapBankNameToCardNumberPrefix(String bankName) throws IllegalAccessException {
        switch (bankName){
            case "HDFC" :
                return "1234";
            case "CITI":
                return "5678";
            case "ICICI":
                return "6789";
            default:
                throw new IllegalAccessException("Invalid Bank Name");
        }
    }

    @Override
    public int mapBankNameToCvvRangeStart(String bankName) throws IllegalAccessException {
        switch (bankName){
            case "HDFC" :
                return 200;
            case "CITI":
                return 500;
            case "ICICI":
                return 700;
            default:
                throw new IllegalAccessException("Invalid Bank Name");
        }

    }

    @Override
    public int mapBankNameToCvvRangeEnd(String bankName) throws IllegalAccessException {
        switch (bankName){
            case "HDFC" :
                return 200;
            case "CITI":
                return 500;
            case "ICICI":
                return 700;
            default:
                throw new IllegalAccessException("Invalid Bank Name");
        }
    }

    @Override
    public LocalDate calculateExpiryDate() {
        return LocalDate.now().plusYears(1).plusMonths(6);
    }

    @Override
    public CreditCardEntity mapToCreditCardEntity(CreditCardDto creditCardDto) {
       CreditCardEntity creditCard = new CreditCardEntity();

       creditCard.setCustomerName(creditCardDto.getName());
       creditCard.setBankName(creditCard.getBankName());
       creditCard.setCardNumber(creditCard.getCardNumber());
       creditCard.setCvv(creditCardDto.getCvv());
       creditCard.setExpiryDate(creditCardDto.getExpiryDate());

       return creditCard;
    }
}
