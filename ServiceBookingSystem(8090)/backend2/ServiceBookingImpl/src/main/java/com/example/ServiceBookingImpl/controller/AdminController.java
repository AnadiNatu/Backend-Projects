package com.example.ServiceBookingImpl.controller;

import com.example.ServiceBookingImpl.dto.AdsDTO;
import com.example.ServiceBookingImpl.dto.ReservationDTO;
import com.example.ServiceBookingImpl.dto.ReviewDTO;
import com.example.ServiceBookingImpl.services.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {


    @Autowired
    private AdminService adminService;


    @GetMapping("/ads/all")
    public ResponseEntity<List<AdsDTO>> getAllAds() {

        return ResponseEntity.ok(new ArrayList<>(adminService.getAllAds()));

    }


    @GetMapping("/reservations/all")
    public ResponseEntity<List<ReservationDTO>> getAllReservations(){

        return ResponseEntity.ok(new ArrayList<>(adminService.getAllReservations()));
    }

    @GetMapping("/reviews/all")
    public ResponseEntity<List<ReviewDTO>> getAllReviews(){

        return ResponseEntity.ok(new ArrayList<>(adminService.getAllReviews()));

    }


    @GetMapping("/ads/{name}")
    public ResponseEntity<List<AdsDTO>> searchByName(@PathVariable  String name){
        return ResponseEntity.ok(new ArrayList<>(adminService.searchByName(name)));
    }


    @GetMapping("/reservation/{companyId}")
    public ResponseEntity<List<ReservationDTO>> getAllAdBooking(Long companyId){
        return ResponseEntity
                .ok(new ArrayList<>(adminService.getAllAdBooking(companyId)));
    }



}