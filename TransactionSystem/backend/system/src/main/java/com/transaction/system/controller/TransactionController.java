package com.transaction.system.controller;


import com.transaction.system.model.TransactionEntity;
import com.transaction.system.service.TransactionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {


    @Autowired
    private TransactionService transactionService;


    @PostMapping("/add")
    public ResponseEntity<TransactionEntity> createTransaction(@RequestBody TransactionEntity transactionEntity){
        TransactionEntity transaction = transactionService.createTransaction(transactionEntity);

        return new ResponseEntity<>(transaction,HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<TransactionEntity>> getTransactionById(@PathVariable Long id){

        Optional<TransactionEntity> transaction = transactionService.getTransactionById(id);

        return ResponseEntity.ok(transaction);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<TransactionEntity>> getAllTransacion(){

        List<TransactionEntity> transaction = transactionService.getAllTransaction();

        return ResponseEntity.ok(transaction);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable Long id){

        transactionService.deleteTransactionById(id);

        return ResponseEntity.noContent().build();
    }

}
