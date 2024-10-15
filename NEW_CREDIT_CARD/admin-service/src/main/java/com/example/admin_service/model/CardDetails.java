package com.example.admin_service.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Card_Details")
@Data
public class CardDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String bankName;
   private String cardType;
   private String cardNumber;
   private int cvv;
   private Date issuedAt;
   private Date expiryDate;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "customer_id")
   private CustomerDetail customerDetail;

   @OneToMany(mappedBy = "cardDetails" , cascade = CascadeType.ALL , orphanRemoval = true)
   private List<Transactions> transactions = new ArrayList<>();

}
