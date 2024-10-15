package com.anadi.ServiceBookingSystem.entity;


import com.anadi.ServiceBookingSystem.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date reviewDate;

    private String review;

    private Long rating;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "user_Id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "ad_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ads ads;


    public ReviewDto getReviewDto(){

        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(id);
        reviewDto.setReview(review);
        reviewDto.setRating(rating);
        reviewDto.setReviewDate(reviewDate);
        reviewDto.setUserId(user.getId());
        reviewDto.setClientName(user.getFname() + " " + user.getLname());
        reviewDto.setServiceName(ads.getServiceName());
        reviewDto.setAdId(ads.getId());

        return reviewDto;

    }
}
