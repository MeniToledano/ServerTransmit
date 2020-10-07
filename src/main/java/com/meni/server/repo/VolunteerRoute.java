package com.meni.server.repo;

import com.meni.server.model.RouteDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Entity                             //why have i needed to add this serializable??
public class VolunteerRoute  implements Serializable {

    @Id
    @GeneratedValue
    private long userId;
    private String fromLocation;
    private String toLocation;
    private String exitTime;
    private String arrivalTime;


    public VolunteerRoute(String fromLocation, String toLocation, String exitTime, String arrivalTime, long userId) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.exitTime = exitTime;
        this.arrivalTime = arrivalTime;
        this.userId = userId;
    }

    public VolunteerRoute(String fromLocation, String toLocation, String exitTime, String arrivalTime ) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.exitTime = exitTime;
        this.arrivalTime = arrivalTime;

    }

    public VolunteerRoute(){    }

    public VolunteerRoute(RouteDto rdt, long userId) {
        this.fromLocation = rdt.getFromLocation();
        this.toLocation = rdt.getToLocation();
        this.exitTime = rdt.getExitTime();
        this.arrivalTime = rdt.getArrivalTime();
        this.userId = userId;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
                +arrivalTime + ' '
                ;
    }
}
