package com.transaction.system.service;

import com.transaction.system.model.TransactionEntity;
import com.transaction.system.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionEntity createTransaction(TransactionEntity transactionEntity){

       return transactionRepository.save(transactionEntity);

    }

    public List<TransactionEntity> getAllTransaction(){

        return transactionRepository.findAll();

    }

    public Optional<TransactionEntity> getTransactionById(Long id){

        return transactionRepository.findById(id);

    }

    public void deleteTransactionById(Long id){

        transactionRepository.deleteById(id);

    }



}
