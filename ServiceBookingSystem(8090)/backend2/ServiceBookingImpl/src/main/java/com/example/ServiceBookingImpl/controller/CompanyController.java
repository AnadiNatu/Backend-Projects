package com.example.ServiceBookingImpl.controller;

import com.example.ServiceBookingImpl.dto.AdsDTO;
import com.example.ServiceBookingImpl.dto.ReservationDTO;
import com.example.ServiceBookingImpl.services.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/company")
@CrossOrigin("*")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/ad/{userId}")
    public ResponseEntity<?> postAd(@PathVariable Long userId , @RequestBody AdsDTO adsDto) throws IOException {

        boolean success = companyService.postAd(userId, adsDto);

        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND ).build();
        }

    }

    @GetMapping("/ads/{userId}")
    public ResponseEntity<?> getAllAdsByUserId(@PathVariable Long userId){

        return ResponseEntity.ok(companyService.getAllAds(userId));

    }


    @GetMapping("ad/{adId}")
    public ResponseEntity<?> getAdById(@PathVariable Long adId){

        AdsDTO adDto = companyService.getAdById(adId);

        if (adDto != null){
            return ResponseEntity.ok(adDto);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

    }


    @PutMapping("/ad/{adId}")
    public ResponseEntity<?> updateAd(@PathVariable Long adId , @ModelAttribute AdsDTO adsDto) throws IOException {

        boolean success = companyService.updateAd(adId, adsDto);

        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/ad/{adId}")
    public ResponseEntity<?> deleteAds(@PathVariable Long adId){

        boolean  success = companyService.deletedAd(adId);

        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


    @GetMapping("/bookings/{companyId}")
    public ResponseEntity<List<ReservationDTO>> getAllAdBooking(@PathVariable Long companyId){

        return ResponseEntity.ok(companyService.getAllAdBooking(companyId));

    }

    @GetMapping("/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId , @PathVariable String status){

        boolean success = companyService.changeBookingStatus(bookingId, status);

        if(success){
            return ResponseEntity.ok().build();
        }else
            return ResponseEntity.notFound().build();

    }

}
