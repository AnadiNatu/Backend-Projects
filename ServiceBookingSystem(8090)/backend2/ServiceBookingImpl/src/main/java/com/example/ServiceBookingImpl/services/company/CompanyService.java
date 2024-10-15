package com.example.ServiceBookingImpl.services.company;

import com.example.ServiceBookingImpl.dto.AdsDTO;
import com.example.ServiceBookingImpl.dto.ReservationDTO;
import com.example.ServiceBookingImpl.entity.Ads;
import com.example.ServiceBookingImpl.entity.Reservation;
import com.example.ServiceBookingImpl.entity.Users;
import com.example.ServiceBookingImpl.enums.ReservationStatus;
import com.example.ServiceBookingImpl.repository.AdsRepository;
import com.example.ServiceBookingImpl.repository.ReservationRepository;
import com.example.ServiceBookingImpl.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private AdsRepository adsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public boolean postAd(Long userId , AdsDTO adsDto) throws IOException {

        Optional<Users> optionalUser = usersRepository.findById(userId);

        if(optionalUser.isPresent()){
            Ads ad = new Ads();
            ad.setServiceName(adsDto.getServiceName());
            ad.setDescription(adsDto.getDescription());
            ad.setPrice(adsDto.getPrice());
            ad.setUsers(optionalUser.get());

            adsRepository.save(ad);
            return true;
        }

        return false;
    }

    public List<AdsDTO> getAllAds(Long userId){

        return adsRepository.findAllByUsersId(userId).stream().map(Ads::getAdsDto).collect(Collectors.toList());

    }

    public AdsDTO getAdById(Long adId){

        Optional<Ads> optionalAd = adsRepository.findById(adId);

        return optionalAd.map(Ads::getAdsDto).orElse(null);

    }

    public boolean updateAd(Long adId , AdsDTO adsDto) throws IOException {

        Optional<Ads> optionalAd = adsRepository.findById(adId);

        if (optionalAd.isPresent()) {

            Ads ads = optionalAd.get();

            ads.setServiceName(adsDto.getServiceName());
            ads.setDescription(adsDto.getDescription());
            ads.setPrice(adsDto.getPrice());

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


    public List<ReservationDTO> getAllAdBooking(Long companyId){

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
