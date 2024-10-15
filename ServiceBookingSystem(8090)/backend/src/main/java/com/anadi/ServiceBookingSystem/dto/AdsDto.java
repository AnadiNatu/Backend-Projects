package com.anadi.ServiceBookingSystem.dto;

import com.anadi.ServiceBookingSystem.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AdsDto {

    private Long id;

    private String serviceName;

    private String description;

    private Double price;

    private MultipartFile img;

    private byte[] returnedImg;
    private Long userId;
    private String companyName;

}
