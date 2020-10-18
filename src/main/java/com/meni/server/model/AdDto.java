package com.meni.server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdDto {

    private long user_id;
    private String title;
    private String description;
    private RouteDto route;

}
