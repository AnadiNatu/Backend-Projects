package com.anadi.ServiceBookingSystem.entity;


import com.anadi.ServiceBookingSystem.dto.AdsDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Ads")
@Data
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;

    private String description;

    private Double price;

//    private MultipartFile img;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;



    public AdsDto getAdsDto(){

        AdsDto adsDto = new AdsDto();

        adsDto.setId(id);
        adsDto.setServiceName(serviceName);
        adsDto.setDescription(description);
        adsDto.setPrice(price);
        adsDto.setCompanyName(user.getFname());
        adsDto.setReturnedImg(img);

        return adsDto;
    }


}
