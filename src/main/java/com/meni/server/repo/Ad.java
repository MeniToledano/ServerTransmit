package com.meni.server.repo;

import com.meni.server.anotations.PersonConverter;
import com.meni.server.anotations.RouteConverter;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;

@Entity
public class Ad {

    @Id
    @GeneratedValue
    private long id;

    @Column
    @Convert(converter = PersonConverter.class)
    private Person p;

    @Column
    @Convert(converter = RouteConverter.class)
    private Route route;

    public Route getRoute() { return route; }

    public void setRoute(Route route) {this.route = route;  }

    public Person getP() { return p;  }

    public void setP(Person p) { this.p = p; }

    public Ad() {  }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}