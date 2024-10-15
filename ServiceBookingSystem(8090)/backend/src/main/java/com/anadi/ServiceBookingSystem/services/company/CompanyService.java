package com.anadi.ServiceBookingSystem.services.company;

import com.anadi.ServiceBookingSystem.dto.AdsDto;
import com.anadi.ServiceBookingSystem.dto.ReservationDto;

import java.io.IOException;
import java.util.List;

public interface CompanyService {
    boolean postAd(Long userId , AdsDto adsDto) throws IOException;

    List<AdsDto> getAllAds(Long userId);

    AdsDto getAdById(Long adId);

    boolean updateAd(Long adId , AdsDto adsDto) throws IOException;

    boolean deletedAd(Long adId);

    List<ReservationDto> getAllAdBooking(Long companyId);

    boolean changeBookingStatus( Long bookingId , String status);

}
