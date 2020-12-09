package com.meni.server.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

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
    private Set<String> roles;
}