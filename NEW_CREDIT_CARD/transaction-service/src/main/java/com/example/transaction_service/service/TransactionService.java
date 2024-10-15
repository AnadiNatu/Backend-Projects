package com.example.transaction_service.service;


import com.example.transaction_service.dtos.TransactionDto;
import com.example.transaction_service.dtos.TransactionResponseDto;

import java.util.List;

public interface TransactionService {

    public TransactionResponseDto saveTransaction(TransactionDto transactionDto);

    public Double sumTransactionAmountByCardType(String cardType);

    public Double sumTransactionAmountByCardTypeAndCustomerId(String cardType , Long customerId);

    public List<TransactionResponseDto> getTransactionByCustomerId(Long customerId);

    public List<TransactionResponseDto> getTransactionByCardType(String cardType);

    public List<TransactionResponseDto> getTransactionByCustomerIdAndCardType(Long customerId , String cardType);
}
