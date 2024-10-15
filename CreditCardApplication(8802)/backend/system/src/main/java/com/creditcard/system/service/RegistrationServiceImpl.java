package com.creditcard.system.service;

import com.creditcard.system.dto.RegistrationDto;
import com.creditcard.system.model.AdminDetails;
import com.creditcard.system.model.CustomerDetails;
import com.creditcard.system.repository.AdminRepository;
import com.creditcard.system.repository.CustomerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    CustomerDetailsRepository customerDetailsRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    VerificationService verificationService;

    @Override
    public CustomerDetails registerCustomer(RegistrationDto registrationDto) throws IllegalAccessException, NoSuchAlgorithmException {

        if (!verifyCustomerDetails(registrationDto)){
            throw new IllegalAccessException("Customer Verification Failed");
        }
        CustomerDetails customer = mapRegistrationDtoCustomer(registrationDto);
        
        return customerDetailsRepository.save(customer);
    }

    @Override
    public AdminDetails registerAdmin(RegistrationDto registrationDto) throws IllegalAccessException, NoSuchAlgorithmException {
        if (!verifyAdminDetails(registrationDto)){
            throw new IllegalAccessException("Customer Verification Failed");
        }
        AdminDetails admin = mapRegistrationDtoAdmin(registrationDto);

        return adminRepository.save(admin);
    }
    
    private boolean verifyAdminDetails(RegistrationDto registrationDto) {

        boolean validEmail = verificationService.verifyEmail(registrationDto.getEmail());
        boolean validPhoneNumber = verificationService.verifyPhoneNumber(registrationDto.getPhoneNumber());
        boolean validAge = verificationService.verifyAge(registrationDto.getAge());
        boolean validName = verificationService.verifyName(registrationDto.getFname()+" "+registrationDto.getLname());

        return validAge && validPhoneNumber && validName && validEmail && registrationDto.getEmail().equals("anadinatu@gmail.com");
    }

    private AdminDetails mapRegistrationDtoAdmin(RegistrationDto registrationDto) throws NoSuchAlgorithmException {

        AdminDetails admin = new AdminDetails();

        String fname = registrationDto.getFname();
        String lname = registrationDto.getLname();
        admin.setName(fname+" "+lname);
        admin.setAge(registrationDto.getAge());
        admin.setPhoneNumber(registrationDto.getPhoneNumber());
        admin.setDateOfBirth(registrationDto.getDateOfBirth());
        admin.setEmail(registrationDto.getEmail());
        admin.setPassword(hashPassword(registrationDto.getPassword()));
        admin.setAddress(registrationDto.getAddress());
        admin.setAddressProof(registrationDto.getAddressProof());

        return admin;

    }


    private boolean verifyCustomerDetails(RegistrationDto registrationDto) {
        boolean validEmail = verificationService.verifyEmail(registrationDto.getEmail());
        boolean validPhoneNumber = verificationService.verifyPhoneNumber(registrationDto.getPhoneNumber());
        boolean validAge = verificationService.verifyAge(registrationDto.getAge());
        boolean duplicateName = duplicateCustomer(registrationDto);

        return validAge && validEmail && validPhoneNumber && duplicateName;
    }

    private CustomerDetails mapRegistrationDtoCustomer(RegistrationDto registrationDto) throws NoSuchAlgorithmException {

        CustomerDetails customer = new CustomerDetails();

        String fname = registrationDto.getFname();
        String lname = registrationDto.getLname();
        customer.setName(fname+" "+lname);
        customer.setAge(registrationDto.getAge());
        customer.setPhoneNumber(registrationDto.getPhoneNumber());
        customer.setDateOfBirth(registrationDto.getDateOfBirth());
        customer.setEmail(registrationDto.getEmail());
        customer.setPassword(hashPassword(registrationDto.getPassword()));
        customer.setAddress(registrationDto.getAddress());
        customer.setAddressProof(registrationDto.getAddressProof());

        return customer;

    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();


        StringBuilder hexString = new StringBuilder();

        for (byte b : digest){
            hexString.append(String.format("%o2x",b));
        }

        return hexString.toString();
    }

    private boolean duplicateCustomer(RegistrationDto registrationDto) {
        String registerationToBeChecked = registrationDto.getFname()+" "+registrationDto.getLname();

        List<CustomerDetails> customerList=customerDetailsRepository.findAll();

        int counter=0;
        for (CustomerDetails customer : customerList){
            if (registerationToBeChecked.equals(customer.getName())){
                counter++;
            }
        }

        if (counter>=1){
            return false;
        }else
            return true;
    }

}
