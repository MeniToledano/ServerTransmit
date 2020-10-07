package com.meni.server.model;

public class AdDto {

    private long id;
    private PersonDto p;
    private RouteDto route;


    public RouteDto getRoute() { return route;  }

    public void setRoute(RouteDto route) {this.route = route;   }

    public PersonDto getP() { return p;    }

    public void setP(PersonDto p) { this.p = p;  }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



}
