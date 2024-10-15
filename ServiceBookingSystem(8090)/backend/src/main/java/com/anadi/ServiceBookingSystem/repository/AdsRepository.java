package com.anadi.ServiceBookingSystem.repository;


import com.anadi.ServiceBookingSystem.dto.AdsDto;
import com.anadi.ServiceBookingSystem.entity.Ads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ads , Long> {


    List<Ads> findAllByUserId(Long userId);

    List<Ads> findAllByServiceNameContaining(String name);
}
