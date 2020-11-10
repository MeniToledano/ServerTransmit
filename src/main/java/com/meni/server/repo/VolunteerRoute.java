package com.meni.server.repo;

import com.meni.server.model.RouteDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "VolunteersRoutes")
public class VolunteerRoute implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CreateTime")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "LastModified")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    private String fromLocation;
    private String toLocation;
    private String exitTime;
    private String arrivalTime;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public VolunteerRoute() { }

    public static RouteDto convertVolunteerRouteToRouteDto(VolunteerRoute route) {
        RouteDto routeDto = new RouteDto();

        routeDto.setExitTime(route.getExitTime());
        routeDto.setRouteId(route.getId());
        routeDto.setArrivalTime(route.getArrivalTime());
        routeDto.setFromLocation(route.getFromLocation());
        routeDto.setToLocation(route.getToLocation());

        return routeDto;
    }

    public static List<RouteDto> convertListOfVolunteerRoutesToListRouteDto(List<VolunteerRoute> list){
        List<RouteDto> convertedList = new LinkedList<>();
        for(VolunteerRoute route : list){
            convertedList.add(convertVolunteerRouteToRouteDto(route));
        }
        return convertedList;
    }


}
