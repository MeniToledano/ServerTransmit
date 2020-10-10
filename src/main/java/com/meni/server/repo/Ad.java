package com.meni.server.repo;

import com.meni.server.anotations.UserConverter;

import javax.persistence.*;

@Entity
@Table(name = "Ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "route_id", referencedColumnName = "requestedRouteId")
    private RequestedRoute route;

    @Convert(converter = UserConverter.class)
    private User user;


    public Ad(User user) {
        this.user = user;
    }

    public Ad() {
    }

    public RequestedRoute getRoute() {
        return route;
    }

    public void setRoute(RequestedRoute route) {
        this.route = route;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}