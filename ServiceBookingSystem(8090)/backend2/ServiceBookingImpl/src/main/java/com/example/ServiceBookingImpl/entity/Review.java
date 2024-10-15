package com.example.ServiceBookingImpl.entity;

import com.example.ServiceBookingImpl.dto.ReviewDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
@Entity
@Table(name = "review")
@RequiredArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date reviewDate;

    private String review;

    private Long rating;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "users_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "ads_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ads ads;


    public ReviewDTO getReviewDTO(){
        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setId(id);
        reviewDTO.setReview(review);
        reviewDTO.setReviewDate(reviewDate);
        reviewDTO.setUserId(user.getId());
        reviewDTO.setClientName(user.getName());
        reviewDTO.setServiceName(ads.getServiceName());
        reviewDTO.setAdId(ads.getId());

        return reviewDTO;
    }

}
