package com.example.ServiceBookingImpl.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdDetailsForClientDTO {

    private AdsDTO adsDto;

    private List<ReviewDTO> reviewDtoList;
}
