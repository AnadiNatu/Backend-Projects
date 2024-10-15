package com.example.card_service.mapper;

import com.example.admin_service.dtos.CardCreateRequest;
import com.example.admin_service.dtos.CardResponseDto;
import com.example.admin_service.model.CardDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class CardMapperImpl implements CardMapper{


    @Override
    public CardResponseDto mapToDto(CardCreateRequest createRequest) throws IllegalAccessException {

        CardResponseDto dto = new CardResponseDto();

        String bankName= createRequest.getBankName();



        String cardPrefix = mapBankNameToCardNumberPrefix(bankName);
        dto.setCardNumber(cardPrefix+generateRandomDigits(12));

        Date cardExpiry = calculateExpiryDate();
        dto.setExpiryDate(cardExpiry);

        dto.setIssuedAt(new Date());

        int cvvStart = mapBankNameToCvvRangeStart(bankName);
        int cvvEnd = mapBankNameToCvvRangeEnd(bankName);
        dto.setCvv(generateRandomNumber(cvvStart,cvvEnd));

        dto.setCardType(createRequest.getCardType());
        dto.setBankName(createRequest.getBankName());
        dto.setCustomerId(createRequest.getCustomerId());


        return dto;

    }



    @Override
    public CardDetails mapToEntity(CardResponseDto cardResponseDto){

        CardDetails cardDetails = new CardDetails();

        cardDetails.setCardNumber(cardResponseDto.getCardNumber());
        cardDetails.setCardType(cardResponseDto.getCardType());
        cardDetails.setBankName(cardResponseDto.getBankName());
        cardDetails.setCvv(cardResponseDto.getCvv());
        cardDetails.setIssuedAt(cardResponseDto.getIssuedAt());
        cardDetails.setExpiryDate(cardResponseDto.getExpiryDate());

        return  cardDetails;
    }

    @Override
    public CardResponseDto mapToResponse(CardDetails cardDetails) {
        CardResponseDto responseDto = new CardResponseDto();

        responseDto.setCardNumber(cardDetails.getCardNumber());
        responseDto.setCardType(cardDetails.getCardType());
        responseDto.setExpiryDate(cardDetails.getExpiryDate());
        responseDto.setIssuedAt(cardDetails.getIssuedAt());
        responseDto.setCvv(cardDetails.getCvv());
        responseDto.setBankName(cardDetails.getBankName());
        responseDto.setCustomerId(cardDetails.getId());

        return responseDto;
    }


    private int generateRandomNumber(int cvvStart, int cvvEnd) {

        Random random = new Random();
        return random.nextInt(cvvEnd-cvvStart+1)+cvvStart;
    }

    private int mapBankNameToCvvRangeEnd(String bankName) throws IllegalAccessException {
        switch (bankName){
            case "HDFC" :
                return 499;
            case "CITI":
                return 699;
            case "ICICI":
                return 899;
            default:
                throw new IllegalAccessException("Invalid Bank Name");
        }
    }
    private int mapBankNameToCvvRangeStart(String bankName) throws IllegalAccessException {
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

    private Date calculateExpiryDate() {

        Date expiry = new Date(System.currentTimeMillis() + 263418);

        return expiry;
    }

    private String generateRandomDigits(int length) {

        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i<length ; i++){
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    private String mapBankNameToCardNumberPrefix(String bankName) throws IllegalAccessException {
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




}
