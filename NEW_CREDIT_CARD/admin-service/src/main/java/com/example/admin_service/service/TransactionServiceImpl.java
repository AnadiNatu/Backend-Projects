package com.example.admin_service.service;

import com.example.admin_service.dtos.TransactionDto;
import com.example.admin_service.dtos.TransactionResponseDto;
import com.example.admin_service.model.CardDetails;
import com.example.admin_service.model.CustomerDetail;
import com.example.admin_service.model.Transactions;
import com.example.admin_service.repository.CardRepository;
import com.example.admin_service.repository.CustomerRepository;
import com.example.admin_service.repository.TransactionRepository;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CardRepository cardRepository;


    @Override
    @Transactional
    public TransactionResponseDto saveTransaction(TransactionDto transactionDto){

        CustomerDetail customer = customerRepository.findById(transactionDto.getCustomerId()).orElseThrow(() -> new IllegalArgumentException("Invalid Customer Id"));

        CardDetails cardDetails = cardRepository.findById(transactionDto.getCardId()).orElseThrow(()->new IllegalArgumentException("Invalid Card Id"));

        Transactions transaction = new Transactions();
        transaction.setCardType(transactionDto.getCardType());
        transaction.setTransactionAmount(transactionDto.getTransactionAmount());
        transaction.setCardDetails(cardDetails);
        transaction.setCustomerDetail(customer);

        Transactions savedTransaction = transactionRepository.save(transaction);

        TransactionResponseDto responseDto = new TransactionResponseDto();

        responseDto.setId(savedTransaction.getId());
        responseDto.setTransactionAmount(savedTransaction.getTransactionAmount());
        responseDto.setCardType(savedTransaction.getCardType());
        responseDto.setCustomerId(savedTransaction.getCustomerDetail().getId());
        responseDto.setCardId(savedTransaction.getCardDetails().getId());

        return responseDto;

    }

    @Override
    @Transactional
    public Double sumTransactionAmountByCardType(String cardType){

        return transactionRepository.sumTransactionAmountByCardType(cardType);
    }

    @Override
    @Transactional
    public Double sumTransactionAmountByCardTypeAndCustomerId(String cardType , Long customerId){

        return transactionRepository.sumTransactionAmountByCardTypeAndCustomerId(cardType, customerId);
    }


    @Override
    @Transactional
    public List<TransactionResponseDto> getTransactionByCustomerId(Long customerId){

        List<Transactions> transactions = transactionRepository.findAll();

        return transactions.stream()
                .filter(transaction -> transaction.getCustomerDetail().getId().equals(customerId))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<TransactionResponseDto> getTransactionByCardType(String cardType){

        List<Transactions> transactions = transactionRepository.findAll();

        return transactions.stream()
                .filter(transaction -> transaction.getCardType().equals(cardType))
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public List<TransactionResponseDto> getTransactionByCustomerIdAndCardType(Long customerId , String cardType){

        List<Transactions> transactions = transactionRepository.findAll();

        return transactions.stream()
                .filter(transaction -> transaction.getCustomerDetail().getId().equals(customerId) && transaction.getCardType().equalsIgnoreCase(cardType))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    private TransactionResponseDto convertToDto(Transactions transactions){

        TransactionResponseDto transactionDto = new TransactionResponseDto();

        transactionDto.setId(transactions.getId());
        transactionDto.setTransactionAmount(transactions.getTransactionAmount());
        transactionDto.setCardType(transactions.getCardType());
        transactionDto.setCustomerId(transactions.getCustomerDetail().getId());
        transactionDto.setCardId(transactions.getCardDetails().getId());

        return transactionDto;
    }

}
