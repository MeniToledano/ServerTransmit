package com.meni.server.repo;

import com.meni.server.anotations.UserConverter;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Volunteers")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "volunteerId")
    private long volunteerId;

    @Column
    @Convert(converter = UserConverter.class)
    private User user;
//
//    @Column
//    @Convert(converter = LinkedListConverter.class)
    @OneToMany(
            mappedBy = "volunteer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<VolunteerRoute> routes;

    public Volunteer(){}

    public Volunteer(User user) {
        this.user = user;
    }
    public Volunteer(User user, List<VolunteerRoute> routes) {
        this.user = user;
        this.routes = routes;
    }

    public Volunteer(Volunteer vole) {
        this.routes = vole.getRoutes();
        this.user = vole.getUser();
    }
    public long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(long volunteerId) {
        this.volunteerId = volunteerId;
    }

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
