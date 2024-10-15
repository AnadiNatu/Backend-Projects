package com.example.ServiceBookingImpl.services.client;

import com.example.ServiceBookingImpl.dto.AdDetailsForClientDTO;
import com.example.ServiceBookingImpl.dto.AdsDTO;
import com.example.ServiceBookingImpl.dto.ReservationDTO;
import com.example.ServiceBookingImpl.dto.ReviewDTO;
import com.example.ServiceBookingImpl.entity.Ads;
import com.example.ServiceBookingImpl.entity.Reservation;
import com.example.ServiceBookingImpl.entity.Review;
import com.example.ServiceBookingImpl.entity.Users;
import com.example.ServiceBookingImpl.enums.ReservationStatus;
import com.example.ServiceBookingImpl.enums.ReviewStatus;
import com.example.ServiceBookingImpl.repository.AdsRepository;
import com.example.ServiceBookingImpl.repository.ReservationRepository;
import com.example.ServiceBookingImpl.repository.ReviewRepository;
import com.example.ServiceBookingImpl.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private AdsRepository adsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<AdsDTO> getAllAds(){

        return adsRepository.findAll().stream().map(Ads::getAdsDto).collect(Collectors.toList());

    }

    public List<AdsDTO> searchByName(String name){

        return adsRepository.findAllByServiceNameContaining(name).stream().map(Ads::getAdsDto).collect(Collectors.toList());

    }

    public boolean bookService(ReservationDTO reservationDto){

        Optional<Ads> optionalAds = adsRepository.findById(reservationDto.getAdId());
        Optional<Users> optionalUser = usersRepository.findById(reservationDto.getUserId());

        if (optionalAds.isPresent() && optionalUser.isPresent()){
            Reservation reservation = new Reservation();

            reservation.setBookDate(reservationDto.getBookDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.setUser(optionalUser.get());

            reservation.setAds(optionalAds.get());
            reservation.setCompany(optionalAds.get().getUsers());
            reservation.setReviewStatus(ReviewStatus.FALSE);

            reservationRepository.save(reservation);

            return true;
        }

        return false;

    }

    public AdDetailsForClientDTO getAdDetailForClientDto(Long adId){

        Optional<Ads> optionalAd = adsRepository.findById(adId);

        AdDetailsForClientDTO adDetailsForClientDto = new AdDetailsForClientDTO();

        if (optionalAd.isPresent()){
            adDetailsForClientDto.setAdsDto(optionalAd.get().getAdsDto());

            List<Review> reviewList = reviewRepository.findAllByAdsId(adId);
            adDetailsForClientDto.setReviewDtoList(reviewList.stream().map(Review::getReviewDTO).collect(Collectors.toList()));
        }

        return adDetailsForClientDto;

    }

    public List<ReservationDTO> getAllBookingByUserId(Long userId){

        return reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());

    }


    public boolean giveReview(ReviewDTO reviewDto){

        Optional<Users> optionalUser = usersRepository.findById(reviewDto.getUserId());
        Optional<Reservation> optionalBooking = reservationRepository.findById(reviewDto.getBookId());

        if (optionalUser.isPresent() && optionalBooking.isPresent()){
            Review review = new Review();

            review.setReviewDate(new Date());
            review.setReview(reviewDto.getReview());
            review.setRating(reviewDto.getRating());

            review.setUser(optionalUser.get());
            review.setAds(optionalBooking.get().getAds());

            reviewRepository.save(review);

            Reservation booking = optionalBooking.get();
            booking.setReviewStatus(ReviewStatus.TRUE);

            reservationRepository.save(booking);
            return true;
        }else {
            return false;
        }
    }



}
