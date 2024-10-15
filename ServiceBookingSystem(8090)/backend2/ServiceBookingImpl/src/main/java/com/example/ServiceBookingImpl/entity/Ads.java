package com.example.ServiceBookingImpl.entity;


import com.example.ServiceBookingImpl.dto.AdsDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ads")
@Data
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;

    private String description;

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "users_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users users;

    public AdsDTO getAdsDto(){

        AdsDTO adsDTO = new AdsDTO();

        adsDTO.setId(id);
        adsDTO.setServiceName(serviceName);
        adsDTO.setDescription(description);
        adsDTO.setPrice(price);
        adsDTO.setCompanyName(users.getName());

        return adsDTO;
    }
}
