package com.meni.server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDto {
//    private String id;
    private String fromLocation;
    private String toLocation;
    private String exitTime;
    private String arrivalTime;

}
