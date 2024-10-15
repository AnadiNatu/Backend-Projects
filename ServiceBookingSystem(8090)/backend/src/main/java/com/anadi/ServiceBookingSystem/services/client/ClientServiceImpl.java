package com.anadi.ServiceBookingSystem.services.client;

import com.anadi.ServiceBookingSystem.dto.AdDetailsForClientDto;
import com.anadi.ServiceBookingSystem.dto.AdsDto;
import com.anadi.ServiceBookingSystem.dto.ReservationDto;
import com.anadi.ServiceBookingSystem.dto.ReviewDto;
import com.anadi.ServiceBookingSystem.entity.Ads;
import com.anadi.ServiceBookingSystem.entity.Reservation;
import com.anadi.ServiceBookingSystem.entity.Review;
import com.anadi.ServiceBookingSystem.entity.User;
import com.anadi.ServiceBookingSystem.enums.ReservationStatus;
import com.anadi.ServiceBookingSystem.enums.ReviewStatus;
import com.anadi.ServiceBookingSystem.repository.AdsRepository;
import com.anadi.ServiceBookingSystem.repository.ReservationRepository;
import com.anadi.ServiceBookingSystem.repository.ReviewRepository;
import com.anadi.ServiceBookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements  ClientService{


    @Autowired
    private AdsRepository adsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<AdsDto> getAllAds(){

        return adsRepository.findAll().stream().map(Ads::getAdsDto).collect(Collectors.toList());

    }

    public List<AdsDto> searchByName(String name){

        return adsRepository.findAllByServiceNameContaining(name).stream().map(Ads::getAdsDto).collect(Collectors.toList());

    }

    public boolean bookService(ReservationDto reservationDto){

        Optional<Ads> optionalAds = adsRepository.findById(reservationDto.getAdId());
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());

        if (optionalAds.isPresent() && optionalUser.isPresent()){
            Reservation reservation = new Reservation();

            reservation.setBookDate(reservationDto.getBookDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.setUser(optionalUser.get());

            reservation.setAds(optionalAds.get());
            reservation.setCompany(optionalAds.get().getUser());
            reservation.setReviewStatus(ReviewStatus.FALSE);

            reservationRepository.save(reservation);

            return true;
        }

        return false;

    }

    public AdDetailsForClientDto getAdDetailForClientDto(Long adId){

        Optional<Ads> optionalAd = adsRepository.findById(adId);

        AdDetailsForClientDto adDetailsForClientDto = new AdDetailsForClientDto();

        if (optionalAd.isPresent()){
            adDetailsForClientDto.setAdsDto(optionalAd.get().getAdsDto());

            List<Review> reviewList = reviewRepository.findAllByAdId(adId);
            adDetailsForClientDto.setReviewDtoList(reviewList.stream().map(Review::getReviewDto).collect(Collectors.toList()));
        }

        return adDetailsForClientDto;

    }

    public List<ReservationDto> getAllBookingByUserId(Long userId){

        return reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());

    }


    public boolean giveReview(ReviewDto reviewDto){

        Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
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
//See if two entry are there in the reservation db . with two review status
            return true;
        }else {
            return false;
        }
    }
}
