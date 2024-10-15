package com.example.admin_service.repository;

import com.example.admin_service.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions , Long> {

    @Query(value = "SELECT SUM(t.transactionAmount) FROM Transactions t WHERE t.cardType = :cardType" , nativeQuery = true)
    Double sumTransactionAmountByCardType(String cardType);

    @Query(value = "SELECT SUM(t.transactionAmount) FROM Transactions t WHERE t.cardType = :cardType AND t.customerDetail.id = :customeId")
    Double sumTransactionAmountByCardTypeAndCustomerId(String cardType , Long customerId);
}
