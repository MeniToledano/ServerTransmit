package com.meni.server.repo;

import com.meni.server.model.RouteDto;
import com.meni.server.model.UserDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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

    public VolunteerRoute() {
    }

    public LocalDateTime getCreateDateTime() { return createDateTime; }
    public LocalDateTime getUpdateDateTime() { return updateDateTime; }

    public VolunteerRoute(String fromLocation, String toLocation, String exitTime, String arrivalTime) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.exitTime = exitTime;
        this.arrivalTime = arrivalTime;

    }

    public VolunteerRoute(RouteDto rdt) {
        this.fromLocation = rdt.getFromLocation();
        this.toLocation = rdt.getToLocation();
        this.exitTime = rdt.getExitTime();
        this.arrivalTime = rdt.getArrivalTime();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return fromLocation + ' '
                + toLocation + ' '
                + exitTime + ' '
                + arrivalTime + ' '
                ;
    }

    public static RouteDto convertVolunteerRouteToRouteDto(VolunteerRoute route) {
        RouteDto routeDto = new RouteDto();

        routeDto.setExitTime(route.getExitTime());
        routeDto.setId(route.getId());
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
