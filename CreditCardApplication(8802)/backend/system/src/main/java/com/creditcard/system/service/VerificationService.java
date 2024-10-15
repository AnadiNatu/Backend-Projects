package com.creditcard.system.service;

public interface VerificationService {

    public boolean verifyEmail(String email);
    public boolean verifyPhoneNumber(String phoneNumber);
    public boolean verifyAge(String age);
    public boolean verifyName(String name);

}
