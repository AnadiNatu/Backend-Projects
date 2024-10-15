package com.creditcard.system.service;

import com.creditcard.system.dto.LoginDto;

import java.security.NoSuchAlgorithmException;

public interface LoginService {

    public boolean authenticate(LoginDto loginDto) throws NoSuchAlgorithmException;
    public boolean authenticateAdmin(LoginDto loginDto) throws NoSuchAlgorithmException;
    public boolean verifyAdminCredentials(LoginDto loginDto) throws NoSuchAlgorithmException;
    public boolean verifyCredentials(LoginDto loginDto) throws NoSuchAlgorithmException;

}
