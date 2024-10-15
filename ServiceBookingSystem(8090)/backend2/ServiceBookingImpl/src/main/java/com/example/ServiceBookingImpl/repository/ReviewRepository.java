package com.example.ServiceBookingImpl.repository;

import com.example.ServiceBookingImpl.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review , Long> {

    List<Review> findAllByAdsId(Long adId);

}
