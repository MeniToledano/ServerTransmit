package com.meni.server.repo;

import com.meni.server.model.AdDto;
import com.meni.server.model.Status;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Ads")
public class Ad{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @Column(name = "CreateTime")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "LastModified")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "route_id", referencedColumnName = "requestedRouteId")
    private RequestedRoute route;

    @OneToOne
    @Nullable
    @JoinColumn(name = "volunteer_id", referencedColumnName = "userId")
    private User volunteerData;

    private String title;
    private String description;

    public Ad() {
        this.status = Status.PENDING;
    }

    public static AdDto convertAdToAdDTO(Ad ad){
        AdDto adDto = new AdDto();

        adDto.setCreateDateTime(ad.getCreateDateTime());
        if (ad.getUpdateDateTime() != null) {
            adDto.setUpdateDateTime(ad.getUpdateDateTime());
        }
        adDto.setDescription(ad.getDescription());
        adDto.setId(ad.getId());
        adDto.setStatus(ad.getStatus().toString());
        adDto.setTitle(ad.getTitle());
        adDto.setUser_id(ad.getUser().getId());
        adDto.setUser(User.convertUserToUserDto(ad.getUser()));
        adDto.setRoute(RequestedRoute.convertRequestedRouteToRouteDto(ad.getRoute()));
        adDto.setVolunteerData(User.convertUserToUserDto(ad.getVolunteerData()));
        return adDto;
    }

    public static List<AdDto> convertListAdsToListAdsDto(List<Ad> ads){
        List<AdDto> adsDTO = new LinkedList<>();
        for (Ad ad : ads){
            adsDTO.add(convertAdToAdDTO(ad));
        }
        return adsDTO;
    }
}