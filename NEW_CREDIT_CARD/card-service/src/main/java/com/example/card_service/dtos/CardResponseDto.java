package com.example.card_service.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CardResponseDto {


    private String bankName;
    private String cardType;
    private String cardNumber;
    private int cvv;
    private Date issuedAt;
    private Date expiryDate;

    private Long customerId;
}
