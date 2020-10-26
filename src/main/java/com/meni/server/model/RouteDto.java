package com.meni.server.model;

import com.meni.server.repo.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RouteDto {

    private long id;
    private String fromLocation;
    private String toLocation;
    private String exitTime;
    private String arrivalTime;

}
