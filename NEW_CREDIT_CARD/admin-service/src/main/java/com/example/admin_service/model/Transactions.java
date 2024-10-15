package com.example.admin_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Transactions")
public class Transactions {

    @Id
    @GeneratedValue
    private Long id;

    private String cardType;

    private Double transactionAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerDetail customerDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private CardDetails cardDetails;

}
