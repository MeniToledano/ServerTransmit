package com.meni.server.model;

import com.meni.server.repo.Ad;
import com.meni.server.repo.User;
import com.meni.server.repo.VolunteerRoute;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private long userId;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private List<RouteDto> routes;
    private List<AdDto> ads;

}