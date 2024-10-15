package com.example.system.dto;

public class CustomerDto {

    private String name ;
    private String bankName;

    public CustomerDto() {
    }

    public CustomerDto(String name, String bankName) {
        this.name = name;
        this.bankName = bankName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
