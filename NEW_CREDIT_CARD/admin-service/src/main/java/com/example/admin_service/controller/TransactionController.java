package com.example.admin_service.controller;

import com.example.admin_service.dtos.TransactionDto;
import com.example.admin_service.dtos.TransactionResponseDto;
import com.example.admin_service.model.Transactions;
import com.example.admin_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/create")
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody TransactionDto transactionDto){

        TransactionResponseDto transactions = transactionService.saveTransaction(transactionDto);

        return ResponseEntity.ok(transactions);
    }


//    /transactions/sum?cardType=Credit
    @GetMapping("/generate/sum")
    public ResponseEntity<Double> generateSumOfTransactionByCardType(@RequestParam String cardType){

        return new ResponseEntity<>(transactionService.sumTransactionAmountByCardType(cardType), HttpStatus.OK);
    }

// "http://yourdomain.com/transactions/sum-by-customer?cardType=Credit&customerId=123"
    @GetMapping("/generate/sum-by-customer")
    public ResponseEntity<Double> generateSumOfTransactionByCardTypeAndCustomer(@RequestParam String cardType , @RequestParam Long customerId){

        return new ResponseEntity<>(transactionService.sumTransactionAmountByCardTypeAndCustomerId(cardType, customerId),HttpStatus.OK);
    }


//    "http://yourdomain.com/transactions/customer/123"
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<TransactionResponseDto>> getTransactionByCustomerId(@PathVariable Long customerId){

        return new ResponseEntity<>(transactionService.getTransactionByCustomerId(customerId) , HttpStatus.OK);
    }


    @GetMapping("/{cardType}")
    public ResponseEntity<List<TransactionResponseDto>> getTransactionByCardType(@PathVariable String cardType){

        return new ResponseEntity<>(transactionService.getTransactionByCardType(cardType) , HttpStatus.OK);

    }

    @GetMapping("/customer/{customerId}/{cardType}")
    public ResponseEntity<List<TransactionResponseDto>> getTransactionByCustomerIdAndCardType(@PathVariable Long customerId , @PathVariable String cardType){

        return new ResponseEntity<>(transactionService.getTransactionByCustomerIdAndCardType(customerId, cardType) , HttpStatus.OK);
    }


}
