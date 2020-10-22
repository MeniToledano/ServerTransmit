package com.meni.server.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdDto {
    private long id;
    private long user_id;
    private String status;
    private String title;
    private String description;
    private RouteDto route;
    private UserDto user;
    private LocalDateTime date;

}
