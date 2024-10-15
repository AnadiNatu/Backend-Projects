package com.example.ServiceBookingImpl.entity;

import com.example.ServiceBookingImpl.dto.ReservationDTO;
import com.example.ServiceBookingImpl.enums.ReservationStatus;
import com.example.ServiceBookingImpl.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
@Table(name = "reservation")
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
    private Users user;


    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "company_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users company;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "ad_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ads ads;




    public ReservationDTO getReservationDto(){

        ReservationDTO dto = new ReservationDTO();

        dto.setId(id);
        dto.setServiceName(ads.getServiceName());
        dto.setBookDate(bookDate);
        dto.setReservationStatus(reservationStatus);
        dto.setReviewStatus(reviewStatus);

        dto.setAdId(ads.getId());
        dto.setCompanyId(company.getId());

        dto.setUserName(user.getName());

        return dto;

    }
}
