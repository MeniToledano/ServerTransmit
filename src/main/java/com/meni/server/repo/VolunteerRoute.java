package com.meni.server.repo;

import com.meni.server.model.RouteDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="VolunteersRoutes")
public class VolunteerRoute  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fromLocation;
    private String toLocation;

    private String exitTime;
    private String arrivalTime;

    @ManyToOne
    @JoinColumn(name="volunteerId")
    private Volunteer volunteer;


    public VolunteerRoute(){ }

    public VolunteerRoute(String fromLocation, String toLocation, String exitTime, String arrivalTime, Volunteer volunteer) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.exitTime = exitTime;
        this.arrivalTime = arrivalTime;
        this.volunteer = volunteer;
    }

    public VolunteerRoute(long id, String fromLocation, String toLocation, String exitTime, String arrivalTime, Volunteer volunteer) {
        this.id = id;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.exitTime = exitTime;
        this.arrivalTime = arrivalTime;
        this.volunteer = volunteer;
    }

    public VolunteerRoute(String fromLocation, String toLocation, String exitTime, String arrivalTime ) {
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
                +arrivalTime + ' '
                ;
    }
}
