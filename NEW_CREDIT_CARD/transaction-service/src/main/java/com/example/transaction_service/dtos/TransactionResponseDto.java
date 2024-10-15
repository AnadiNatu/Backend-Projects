package com.example.transaction_service.dtos;

import lombok.Data;

@Data
public class TransactionResponseDto {

    private Long id;

    private String cardType;

    private Double transactionAmount;

    private Long customerId;

    private Long cardId;
}
