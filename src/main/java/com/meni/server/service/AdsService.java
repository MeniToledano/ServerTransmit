package com.meni.server.service;

import com.meni.server.exception.AdNotFoundException;
import com.meni.server.model.AdDto;
import com.meni.server.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AdsService {
    @Autowired
    AdsRepository repository;
    @Autowired
    RequestedRoutesRepository routesRepo;

    public void add(AdDto dto) {
        Ad ad = toEntity(dto);
        repository.save(ad);

//        handleAdRequests hr = new handleAdRequests();
//        User u = hr.add(ad);//not elegant, prefer handlig all the request in diff section
//        return u;
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Ad> getAds() {
        return (List<Ad>) repository.findAll();
    }

    public Ad getAdById(long id) {
        Optional<Ad> optionalAd = repository.findById(id);
        return optionalAd.orElseThrow(() -> new AdNotFoundException("Couldn't find a Ad with id: " + id));
    }

    private Ad toEntity(AdDto dto) {

        Ad entity = new Ad();

        entity.setRoute(new RequestedRoute(dto.getRoute()));
        entity.setUser(new User(dto.getUser()));
 //       entity.getRoute().setAd(entity);
        //shouldnt I call here to the SERVICE insted of REPO???
//        RequestedRoute r = new RequestedRoute(dto.getRoute());
//        r.setRequestedRouteId(entity.getId());
//        routesRepo.save(r);

        return entity;
    }
}