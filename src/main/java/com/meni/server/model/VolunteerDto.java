package com.meni.server.model;


import com.meni.server.repo.User;
import com.meni.server.repo.VolunteerRoute;

import java.util.LinkedList;

public class VolunteerDto {
    private User user;
    private LinkedList<VolunteerRoute> routes = new LinkedList<VolunteerRoute>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LinkedList<VolunteerRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(LinkedList<VolunteerRoute> routes) {
        this.routes = routes;
    }
}
