package com.anadi.ServiceBookingSystem.entity;

import com.anadi.ServiceBookingSystem.dto.ReservationDto;
import com.anadi.ServiceBookingSystem.enums.ReservationStatus;
import com.anadi.ServiceBookingSystem.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ReservationStatus reservationStatus;

    private ReviewStatus reviewStatus;

    private Date bookDate;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "user_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "company_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User company;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "ad_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ads ads;


    public ReservationDto getReservationDto(){

        ReservationDto dto = new ReservationDto();

        dto.setId(id);
        dto.setServiceName(ads.getServiceName());
        dto.setBookDate(bookDate);
        dto.setReservationStatus(reservationStatus);
        dto.setReviewStatus(reviewStatus);

        dto.setAdId(ads.getId());
        dto.setCompanyId(company.getId());
        dto.setUserName(user.getFname() + user.getLname());

        return dto;
    }

}
