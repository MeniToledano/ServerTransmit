package com.meni.server.repo;

import com.meni.server.model.AdDto;
import com.meni.server.model.Status;
import com.sun.istack.Nullable;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


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

    public Ad(User user) {
        this.user = user;
    }

    public Ad() {
        this.status = Status.PENDING;
    }


    public User getVolunteerData() {
        return volunteerData;
    }

    public void setVolunteerData(User volunteerData) {
        this.volunteerData = volunteerData;
    }
    public LocalDateTime getCreateDateTime() { return createDateTime; }
    public LocalDateTime getUpdateDateTime() { return updateDateTime; }

    public RequestedRoute getRoute() { return route; }

    public void setRoute(RequestedRoute route) { this.route = route; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getTitle() { return title;  }

    public void setTitle(String title) { this.title = title;  }

    public String getDescription() { return description;  }

    public String getStatus() { return status.name();  }

    public void setStatus(Status status) { this.status = status; }

    public void setDescription(String description) {  this.description = description; }

    public static AdDto convertAdToAdDTO(Ad ad){
        AdDto adDto = new AdDto();

        adDto.setCreateDateTime(ad.getCreateDateTime());
        if (ad.getUpdateDateTime() != null) {
            adDto.setUpdateDateTime(ad.getUpdateDateTime());
        }
        adDto.setDescription(ad.getDescription());
        adDto.setId(ad.getId());
        adDto.setStatus(ad.getStatus());
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