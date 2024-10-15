package com.anadi.ServiceBookingSystem.services.client;

import com.anadi.ServiceBookingSystem.dto.AdDetailsForClientDto;
import com.anadi.ServiceBookingSystem.dto.AdsDto;
import com.anadi.ServiceBookingSystem.dto.ReservationDto;
import com.anadi.ServiceBookingSystem.dto.ReviewDto;

import java.util.List;

public interface ClientService {

    List<AdsDto> getAllAds();

    List<AdsDto> searchByName(String name);

    boolean bookService(ReservationDto reservationDto);

    AdDetailsForClientDto getAdDetailForClientDto(Long adId);

    List<ReservationDto> getAllBookingByUserId(Long userId);

    boolean giveReview(ReviewDto reviewDto);

}
