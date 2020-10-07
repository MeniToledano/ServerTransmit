package com.meni.server.service;

import com.meni.server.exception.AdNotFoundException;
import com.meni.server.model.AdDto;
import com.meni.server.model.PersonDto;
import com.meni.server.model.RouteDto;
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
    RoutesRepository routesRepo;

    public void add(AdDto dto) {
        repository.save(toEntity(dto));
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

        entity.setRoute(new Route(dto.getRoute(), entity.getId()));
        entity.setP(new Person(dto.getP(),entity.getId()));

        routesRepo.save(new Route(dto.getRoute() , entity.getId()));

        return entity;
    }
}