package com.example.ServiceBookingImpl.controller;

import com.example.ServiceBookingImpl.dto.AdsDTO;
import com.example.ServiceBookingImpl.dto.ReservationDTO;
import com.example.ServiceBookingImpl.dto.ReviewDTO;
import com.example.ServiceBookingImpl.services.client.ClientService;
import com.example.ServiceBookingImpl.services.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/ads")
    public ResponseEntity<?> getAllAds(){

        return  ResponseEntity.ok(clientService.getAllAds());

    }


    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchByServiceName(@PathVariable String name){

        return ResponseEntity.ok(clientService.searchByName(name));

    }

    @PostMapping("/book-service")
    public ResponseEntity<?> bookService(@RequestBody ReservationDTO reservationDto ){

        boolean success = clientService.bookService(reservationDto);

        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }

    @GetMapping("/ad/{adId}")
    public ResponseEntity<?> getAdDetailsByAdId(@PathVariable Long adId){

        return ResponseEntity.ok(clientService.getAdDetailForClientDto(adId));

    }


    @GetMapping("/my-booking/{userId}")
    public ResponseEntity<?> getAllBookingByUserId(@PathVariable Long userId){

        return ResponseEntity.ok(clientService.getAllBookingByUserId(userId));

    }



    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@RequestBody ReviewDTO reviewDto){

        Boolean success = clientService.giveReview(reviewDto);

        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


}
