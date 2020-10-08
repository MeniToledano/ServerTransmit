package com.meni.server.repo;

import com.meni.server.model.UserDto;

public class User {

    private String name;
    private String lastName;
    private String phone;
    private String eMail;


    public User(){}

    public User(String name, String lastName, String phone, String eMail) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.eMail = eMail;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }


}
