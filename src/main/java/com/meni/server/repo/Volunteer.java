package com.meni.server.repo;

import com.meni.server.anotations.LinkedListConverter;
import com.meni.server.anotations.UserConverter;
import com.meni.server.model.VolunteerDto;

import javax.persistence.*;
import java.util.LinkedList;

@Entity
public class Volunteer {

    @Id
    @GeneratedValue
    private long id;

    @Column
    @Convert(converter = UserConverter.class)
    private User user;

    @Column
    @Convert(converter = LinkedListConverter.class)
    private LinkedList<VolunteerRoute> routes = new LinkedList<VolunteerRoute>();

    public Volunteer(){}

    public Volunteer(User user, LinkedList<VolunteerRoute> routes) {
        this.user = user;
        this.routes = routes;
    }

    public Volunteer(VolunteerDto vole) {
        this.routes = vole.getRoutes();
        this.user = vole.getUser();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
