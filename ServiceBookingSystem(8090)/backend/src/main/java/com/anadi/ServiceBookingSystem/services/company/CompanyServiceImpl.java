package com.anadi.ServiceBookingSystem.services.company;


import com.anadi.ServiceBookingSystem.dto.AdsDto;
import com.anadi.ServiceBookingSystem.dto.ReservationDto;
import com.anadi.ServiceBookingSystem.entity.Ads;
import com.anadi.ServiceBookingSystem.entity.Reservation;
import com.anadi.ServiceBookingSystem.entity.User;
import com.anadi.ServiceBookingSystem.enums.ReservationStatus;
import com.anadi.ServiceBookingSystem.repository.AdsRepository;
import com.anadi.ServiceBookingSystem.repository.ReservationRepository;
import com.anadi.ServiceBookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private AdsRepository adsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    public boolean postAd(Long userId , AdsDto adsDto) throws IOException {

        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent()){
            Ads ad = new Ads();
            ad.setServiceName(adsDto.getServiceName());
            ad.setDescription(adsDto.getDescription());
            ad.setImg(adsDto.getImg().getBytes());
            ad.setPrice(adsDto.getPrice());
            ad.setUser(optionalUser.get());

            adsRepository.save(ad);
            return true;
        }

        return false;
    }

    public List<AdsDto> getAllAds(Long userId){

        return adsRepository.findAllByUserId(userId).stream().map(Ads::getAdsDto).collect(Collectors.toList());

    }

    public AdsDto getAdById(Long adId){

        Optional<Ads> optionalAd = adsRepository.findById(adId);

        if (optionalAd.isPresent()){
            return optionalAd.get().getAdsDto();
        }
        return null;

    }

    public boolean updateAd(Long adId , AdsDto adsDto) throws IOException {

        Optional<Ads> optionalAd = adsRepository.findById(adId);

        if (optionalAd.isPresent()) {

            Ads ads = optionalAd.get();

            ads.setServiceName(adsDto.getServiceName());
            ads.setDescription(adsDto.getDescription());
            ads.setPrice(adsDto.getPrice());


            if (adsDto.getImg() != null) {
                ads.setImg(adsDto.getImg().getBytes());
            }

            adsRepository.save(ads);

            return true;

        } else {

            return false;
        }

    }


    public boolean deletedAd(Long adId){

        Optional<Ads> optionalAds = adsRepository.findById(adId);

        if (optionalAds.isPresent()){

            adsRepository.delete(optionalAds.get());

            return true;
        }
        return false;
    }


    public List<ReservationDto> getAllAdBooking(Long companyId){

        return reservationRepository.findAllByCompanyId(companyId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());

    }

    public boolean changeBookingStatus( Long bookingId , String status){

        Optional<Reservation> optionalReservation = reservationRepository.findById(bookingId);

        if (optionalReservation.isPresent()){

            Reservation existingReservation = optionalReservation.get();

            if (Objects.equals(status ,"Approved")){

                existingReservation.setReservationStatus(ReservationStatus.APPROVED);
            }else{
                existingReservation.setReservationStatus(ReservationStatus.REJECTED);
            }

            reservationRepository.save(existingReservation);

            return true;
        }

        return false;

    }

}
