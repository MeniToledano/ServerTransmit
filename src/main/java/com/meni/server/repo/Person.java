package com.meni.server.repo;

import com.meni.server.model.PersonDto;

public class Person {


    private String name;
    private String lastName;
    private String phone;
    private String eMail;
    private long  adId;

    public Person (PersonDto p, long adId){
        this.name = p.getName();
        this.lastName = p.getLastName();
        this.phone = p.getPhone();
        this.eMail = p.geteMail();
        this.adId = adId;
    }
    public Person(String name, String lastName, String phone, String eMail, long adId) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.eMail = eMail;
        this.adId = adId;
    }


    public long getAdId() {
        return adId;
    }

    public void setAdId(long adId) {
        this.adId = adId;
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
