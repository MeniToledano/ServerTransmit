package com.meni.server.model;

import com.meni.server.repo.Ad;
import com.meni.server.repo.User;
import com.meni.server.repo.VolunteerRoute;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserDto {

    private long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String userName;
    private String password;


}