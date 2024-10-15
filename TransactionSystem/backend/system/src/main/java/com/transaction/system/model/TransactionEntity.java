package com.transaction.system.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "Transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cardNumber;
    private String cardType;
    private String expDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private AmountEntity amount;

    public TransactionEntity() {
    }

    public TransactionEntity(Long id, String name, String cardNumber, String cardType, String expDate, AmountEntity amount) {
        this.id = id;
        this.name = name;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.expDate = expDate;
        this.amount = amount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public AmountEntity getAmount() {
        return amount;
    }

    public void setAmount(AmountEntity amount) {
        this.amount = amount;
    }
}
