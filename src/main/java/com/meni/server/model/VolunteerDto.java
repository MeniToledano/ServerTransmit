package com.meni.server.model;


import com.meni.server.repo.User;
import com.meni.server.repo.VolunteerRoute;

import java.util.List;

public class VolunteerDto {
    private User user;
    private List<VolunteerRoute> routes;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<VolunteerRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(List<VolunteerRoute> routes) {
        this.routes = routes;
    }
}
