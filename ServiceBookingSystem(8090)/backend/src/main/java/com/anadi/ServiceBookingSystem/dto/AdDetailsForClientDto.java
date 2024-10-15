package com.anadi.ServiceBookingSystem.dto;


import lombok.Data;

import java.util.List;

@Data
public class AdDetailsForClientDto {

    private AdsDto adsDto;

    private List<ReviewDto> reviewDtoList;



}
