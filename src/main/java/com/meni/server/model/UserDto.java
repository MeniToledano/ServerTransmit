package com.meni.server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String userId;
    private String name;
    private String lastName;
    private String phone;
    private String email;

}