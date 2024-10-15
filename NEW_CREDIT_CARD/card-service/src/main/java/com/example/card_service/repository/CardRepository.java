package com.example.card_service.repository;

import com.example.card_service.model.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardDetails , Long> {

    CardDetails findByBankName(String bankName);
}
