package com.meni.server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDto {

    private long routeId;
    private String fromLocation;
    private String toLocation;
    private String exitTime;
    private String arrivalTime;

}
