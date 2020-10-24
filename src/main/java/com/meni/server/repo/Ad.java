package com.meni.server.repo;

import com.meni.server.model.AdDto;
import com.meni.server.model.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name = "Ads")
public class Ad implements Comparable<Ad>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "route_id", referencedColumnName = "requestedRouteId")
    private RequestedRoute route;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String title;
    private String description;

    public Ad(User user) {
        this.user = user;
    }

    public Ad() {
        this.status = Status.PENDING;
        this.date = LocalDateTime.now();
    }

    public LocalDateTime getDate() { return date; }

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

    @Override
    public int compareTo(Ad ad) {
        LocalDateTime date = ad.getDate();
        return date.compareTo(this.date);
    }

    public static AdDto convertAdToAdDTO(Ad ad){
        AdDto adDto = new AdDto();
        adDto.setDate(ad.getDate());
        adDto.setDescription(ad.getDescription());
        adDto.setId(ad.getId());
        adDto.setStatus(ad.getStatus());
        adDto.setTitle(ad.getTitle());
        adDto.setUser_id(ad.getUser().getId());
        adDto.setUser(User.convertUserToUserDto(ad.getUser()));
        adDto.setRoute(RequestedRoute.convertRequestedRouteToRouteDto(ad.getRoute()));
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