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

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;



    private String status;

    private String title;

    private String description;

    public Ad(User user) {
        this.user = user;
    }

    public Ad() {
        this.status = "PENDING";
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

    public String getTitle() { return title;  }

    public void setTitle(String title) { this.title = title;  }

    public String getDescription() { return description;  }

    public String getStatus() { return status;  }

    public void setStatus(String status) { this.status = status; }

    public void setDescription(String description) {  this.description = description; }
}