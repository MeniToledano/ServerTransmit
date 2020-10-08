package com.meni.server.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VolunteerDto {

    private UserDto user;

    private List<RouteDto> routes;



}
