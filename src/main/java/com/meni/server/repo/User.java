package com.meni.server.repo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meni.server.model.UserDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Users")
public class User{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserId")
    private long id;

    @Column(name = "CreateTime")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column(name = "LastModified")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    private String name;
    private String lastName;
    private String phone;
    private String eMail;

    public List<VolunteerRoute> getRoutes() {
        return routes;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<VolunteerRoute> routes;

    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
    private List<Ad> ads;

    public User() {
        routes = new LinkedList<>();
    }

    public User(String name, String lastName, String phone, String eMail) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.eMail = eMail;
    }

    public LocalDateTime getCreateDateTime() { return createDateTime; }
    public LocalDateTime getUpdateDateTime() { return updateDateTime; }

    public void addRoutes(List<VolunteerRoute> listOfRoutes){
        for(VolunteerRoute route : listOfRoutes) {
            routes.add(route);
        }
    }

    public void addAd(Ad ad){
        this.ads.add(ad);
    }

    public List<Ad> getAds(){
      return this.ads;
    }

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

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public long getId() {
        return id;
    }

    public static UserDto convertUserToUserDto(User user){
        if(user == null){return null;}
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEMail());
        userDto.setLastName(user.getLastName());
        userDto.setName(user.getName());
        userDto.setPhone(user.getPhone());
        userDto.setUserId(user.getId());

        return userDto;
    }

    public static List<UserDto> convertListUsersToListUsersDto(List<User> list){
        List<UserDto> newList = new LinkedList<>();
        for (User user : list){
            newList.add(convertUserToUserDto(user));
        }
        return newList;
    }
}
