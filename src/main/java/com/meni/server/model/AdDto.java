package com.meni.server.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdDto {
    private long adId;
    private long userId;
    private String status;
    private String title;
    private String description;
    private RouteDto route;
    private UserDto user;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private UserDto volunteerData;

}
