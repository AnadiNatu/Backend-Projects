package com.example.admin_service.dtos;

import lombok.Data;

@Data
public class CardCreateRequest {

    private String bankName;

    private String cardType;

    private Long customerId;
}
