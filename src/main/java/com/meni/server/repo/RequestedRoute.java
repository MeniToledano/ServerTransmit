package com.meni.server.repo;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "route")
    private Ad ad;

    public RequestedRoute(String fromLocation, String toLocation, String exitTime, String arrivalTime) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.exitTime = exitTime;
        this.arrivalTime = arrivalTime;

    }

    public RequestedRoute() {
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
}
