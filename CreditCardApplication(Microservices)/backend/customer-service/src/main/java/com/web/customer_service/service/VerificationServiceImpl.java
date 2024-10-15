package com.web.customer_service.service;

import org.springframework.stereotype.Service;

@Service
public class VerificationServiceImpl implements VerificationService {
    @Override
    public boolean verifyEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        return email.matches(emailRegex);
    }

    @Override
    public boolean verifyPhoneNumber(String phoneNumber) {

        String phoneRegex="^\\d{10}$";

        return phoneNumber.matches(phoneRegex);
    }

    @Override
    public boolean verifyAge(String age) {

        int ageNum = Integer.parseInt(age);

        return ageNum>=18 &&  ageNum<=100 ;
    }

    @Override
    public boolean verifyName(String name) {

        String adminName = "Anadi Natu";

        return name.equals(adminName);
    }
}
