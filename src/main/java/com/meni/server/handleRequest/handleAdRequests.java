package com.meni.server.handleRequest;

import com.meni.server.repo.Ad;
import com.meni.server.repo.User;
import com.meni.server.repo.VolunteerRoute;
import com.meni.server.rest.AdController;
import com.meni.server.rest.VolunteerController;
import com.meni.server.rest.VolunteerRoutesController;

import java.util.List;

public class handleAdRequests {

    //All these controllers are null, how to handle the req?
    private VolunteerRoutesController vrc = new VolunteerRoutesController();
    private AdController ac = new AdController();
    private VolunteerController vc = new VolunteerController();


    public User add(Ad ad){
        String from = ad.getRoute().getFromLocation();
        String to = ad.getRoute().getToLocation();
        List<VolunteerRoute> vr = vrc.getRoutes();
        User volunteer = null;
        for(VolunteerRoute route : vr){
            if(route.getFromLocation().equals(from)){
                volunteer = (vc.getById(route.getId())).getUser() ;
            }
        }
        return volunteer;

    }
}
