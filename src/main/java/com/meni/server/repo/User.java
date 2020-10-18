package com.meni.server.repo;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Users")
public class User{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserId")
    private long id;

    private String name;
    private String lastName;
    private String phone;
    private String eMail;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<VolunteerRoute> routes;

    @OneToOne(mappedBy = "route")
    private Ad ad;

    public User() {
        routes = new LinkedList<>();
    }

    public User(String name, String lastName, String phone, String eMail) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.eMail = eMail;
    }

    public void addRoutes(List<VolunteerRoute> listOfRoutes){
        for(VolunteerRoute route : listOfRoutes) {
            System.out.println("asd");

            routes.add(route);
            System.out.println("asd");
        }
    }
//    public void removeRoute(VolunteerRoute route){
//        routes.remove(route);
//       // route.setUser(null);
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public long getId() {
        return id;
    }
}
