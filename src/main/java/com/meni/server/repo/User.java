package com.meni.server.repo;

import com.meni.server.model.UserDto;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

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

    @Column(name = "UserName")
    private String userName;
    private String password;

    @NotNull //added
    @Column(name = "Email")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<VolunteerRoute> routes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Ad> ads;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
        routes = new LinkedList<>();
    }

    public static UserDto convertUserToUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setLastName(user.getLastName());
        userDto.setFirstName(user.getName());
        userDto.setPhone(user.getPhone());
        userDto.setUserId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setUserName(user.getUserName());

        // should be handled differently
        Set<String> s = new HashSet<>();
        s.add("USER");
        userDto.setRoles(s);
        // todo: handle roles here
        return userDto;
    }

    public static List<UserDto> convertListUsersToListUsersDto(List<User> list) {
        List<UserDto> newList = new LinkedList<>();
        for (User user : list) {
            newList.add(convertUserToUserDto(user));
        }
        return newList;
    }

    public void addRoutes(List<VolunteerRoute> listOfRoutes) {
        for (VolunteerRoute route : listOfRoutes) {
            routes.add(route);
        }
    }

    public void addAd(Ad ad) {
        this.ads.add(ad);
    }
}
