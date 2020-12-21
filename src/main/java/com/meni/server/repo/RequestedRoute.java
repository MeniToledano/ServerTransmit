package com.meni.server.repo;

import com.meni.server.model.RouteDto;
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
@Table(name = "RequestedRoutes")
public class RequestedRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestedRouteId;

    private String fromLocation;
    private String toLocation;
    private String exitTime;
    private String arrivalTime;

    @Column(name = "CreateTime")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "LastModified")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @OneToOne(mappedBy = "route")
    private Ad ad;

    public RequestedRoute() { }

    public static RouteDto convertRequestedRouteToRouteDto(RequestedRoute route) {
        RouteDto routeDto = new RouteDto();

        routeDto.setExitTime(route.getExitTime());
        routeDto.setRouteId(route.getRequestedRouteId());
        routeDto.setArrivalTime(route.getArrivalTime());
        routeDto.setFromLocation(route.getFromLocation());
        routeDto.setToLocation(route.getToLocation());

        return routeDto;
    }

    public static List<RouteDto> convertListOfRequestedRoutesToListRouteDto(List<RequestedRoute> list){
        List<RouteDto> convertedList = new LinkedList<>();
        list.forEach(requestedRoute -> convertedList.add(convertRequestedRouteToRouteDto(requestedRoute)));
        return convertedList;
    }
}
