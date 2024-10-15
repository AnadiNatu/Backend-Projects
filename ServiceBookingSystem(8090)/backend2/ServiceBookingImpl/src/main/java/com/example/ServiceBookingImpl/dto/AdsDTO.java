package com.example.ServiceBookingImpl.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AdsDTO {
    private Long id;

    private String serviceName;

    private String description;

    private Double price;

    private Long userId;
    private String companyName;
}
