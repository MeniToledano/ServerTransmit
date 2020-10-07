package com.meni.server.repo;

import com.meni.server.anotations.UserConverter;
import com.meni.server.anotations.RouteConverter;

import javax.persistence.*;

@Entity
public class Ad {

    @Id
    @GeneratedValue
    private long id;

    @Column
    @Convert(converter = UserConverter.class)
    private User user;

    @Column
    @Convert(converter = RouteConverter.class)
    private RequestedRoute route;

    public RequestedRoute getRoute() { return route; }

    public void setRoute(RequestedRoute route) {this.route = route;  }

    public User getUser() { return user;  }

    public void setUser(User user) { this.user = user; }

    public Ad() {  }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}