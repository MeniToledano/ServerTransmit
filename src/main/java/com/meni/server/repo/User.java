package com.meni.server.repo;

import com.meni.server.model.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserId")
    private long id;

    @OneToOne(mappedBy = "volunteerData")
    private Ad ad;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<VolunteerRoute> routes;

    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST)
    private List<Ad> ads;

    public User() {
        routes = new LinkedList<>();
    }

    public void addRoutes(List<VolunteerRoute> listOfRoutes){
        for(VolunteerRoute route : listOfRoutes) {
            routes.add(route);
        }
    }

    public void addAd(Ad ad){ this.ads.add(ad); }

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
