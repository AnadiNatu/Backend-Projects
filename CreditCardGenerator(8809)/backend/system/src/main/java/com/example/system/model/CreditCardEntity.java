package com.example.system.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class CreditCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerId")
    private Long id;

    @Column(name = "Card Number")
    private String cardNumber;

    @Column(name = "Expiry Date")
    private LocalDate expiryDate;

    @Column(name = "CVV")
    private int cvv;

    @Column(name = "Customer Name")
    private String customerName;

    @Column(name = "Bank Name")
    private String bankName;


    public CreditCardEntity() {
    }

    public CreditCardEntity(Long id, String cardNumber, LocalDate expiryDate, int cvv, String customerName, String bankName) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.customerName = customerName;
        this.bankName = bankName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
