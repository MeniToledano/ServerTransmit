package com.meni.server.repo;

import com.meni.server.model.RouteDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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

    public Ad getAd() {
        return ad;
    }



    public RequestedRoute(String fromLocation, String toLocation, String exitTime, String arrivalTime) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.exitTime = exitTime;
        this.arrivalTime = arrivalTime;

    }

    public RequestedRoute() {
    }

    public LocalDateTime getCreateDateTime() { return createDateTime; }
    public LocalDateTime getUpdateDateTime() { return updateDateTime; }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    //public Ad getAd() { return ad; }

    public long getRequestedRouteId() {
        return requestedRouteId;
    }

    public void setRequestedRouteId(long requestedRouteId) {
        this.requestedRouteId = requestedRouteId;
    }

    @Override
    public String toString() {
        return fromLocation + ' '
                + toLocation + ' '
                + exitTime + ' '
                + arrivalTime + ' '
                ;
    }

    public static RouteDto convertRequestedRouteToRouteDto(RequestedRoute route) {
        RouteDto routeDto = new RouteDto();

        routeDto.setExitTime(route.getExitTime());
        routeDto.setId(route.getRequestedRouteId());
        routeDto.setArrivalTime(route.getArrivalTime());
        routeDto.setFromLocation(route.getFromLocation());
        routeDto.setToLocation(route.getToLocation());

        return routeDto;
    }

    public static List<RouteDto> convertListOfRequestedRoutesToListRouteDto(List<RequestedRoute> list){
        List<RouteDto> convertedList = new LinkedList<>();
        for(RequestedRoute route : list){
            convertedList.add(convertRequestedRouteToRouteDto(route));
        }
        return convertedList;
    }
}
