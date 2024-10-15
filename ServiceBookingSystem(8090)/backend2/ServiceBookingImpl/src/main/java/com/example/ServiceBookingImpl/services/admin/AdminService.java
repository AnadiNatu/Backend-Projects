package com.example.ServiceBookingImpl.services.admin;

import com.example.ServiceBookingImpl.dto.AdsDTO;
import com.example.ServiceBookingImpl.dto.ReservationDTO;
import com.example.ServiceBookingImpl.dto.ReviewDTO;
import com.example.ServiceBookingImpl.entity.Ads;
import com.example.ServiceBookingImpl.entity.Reservation;
import com.example.ServiceBookingImpl.entity.Review;
import com.example.ServiceBookingImpl.repository.AdsRepository;
import com.example.ServiceBookingImpl.repository.ReservationRepository;
import com.example.ServiceBookingImpl.repository.ReviewRepository;
import com.example.ServiceBookingImpl.repository.UsersRepository;
import com.example.ServiceBookingImpl.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {


    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AdsRepository adsRepository;


    public List<AdsDTO> getAllAds(){

        return adsRepository.findAll().stream().map(Ads::getAdsDto).collect(Collectors.toList());

    }

    public List<ReservationDTO> getAllReservations(){

        return reservationRepository.findAll().stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    public List<ReviewDTO> getAllReviews(){
        return reviewRepository.findAll().stream().map(Review::getReviewDTO).collect(Collectors.toList());
    }

    public List<AdsDTO> searchByName(String name){

        return adsRepository.findAllByServiceNameContaining(name).stream().map(Ads::getAdsDto).collect(Collectors.toList());

    }

    public List<ReservationDTO> getAllAdBooking(Long companyId){

        return reservationRepository.findAllByCompanyId(companyId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());

    }

}
