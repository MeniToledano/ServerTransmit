package com.meni.server.model;

import lombok.Getter;
import lombok.Setter;

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