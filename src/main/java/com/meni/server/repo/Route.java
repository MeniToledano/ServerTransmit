package com.meni.server.repo;

import com.meni.server.model.RouteDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Route {

    private String fromLocation;
    private String toLocation;
    private String exitTime;
    private String arrivalTime;
    @Id
    @GeneratedValue
    private long adId;

    public Route(String fromLocation, String toLocation, String exitTime, String arrivalTime) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.exitTime = exitTime;
        this.arrivalTime = arrivalTime;
    }
    public Route(){    }

    public Route(RouteDto rdt, long adId) {
        this.fromLocation = rdt.getFromLocation();
        this.toLocation = rdt.getToLocation();
        this.exitTime = rdt.getExitTime();
        this.arrivalTime = rdt.getArrivalTime();
        this.adId = adId;
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



}
