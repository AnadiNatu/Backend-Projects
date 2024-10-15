package com.example.transaction_service.dtos;

import lombok.Data;

@Data
public class TransactionDto {


    private Long id;

    private String cardType;

    private Double transactionAmount;

    private Long customerId;

    private Long cardId;
}
