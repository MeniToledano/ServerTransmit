package com.meni.server.service;

import com.meni.server.exception.AdNotFoundException;
import com.meni.server.model.AdDto;
import com.meni.server.model.RouteDto;
import com.meni.server.model.UserDto;
import com.meni.server.repo.Ad;
import com.meni.server.repo.AdsRepository;
import com.meni.server.repo.RequestedRoute;
import com.meni.server.repo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AdsService {
    @Autowired
    AdsRepository repository;

    public void add(AdDto dto) {
        Ad ad = toEntity(dto);
        repository.save(ad);
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
        UserDto udt = dto.getUser();
        RouteDto routeDto = dto.getRoute();
        entity.setRoute(new RequestedRoute(routeDto.getFromLocation(), routeDto.getToLocation(), routeDto.getExitTime(), routeDto.getArrivalTime()));
        entity.setUser(new User(udt.getName(), udt.getLastName(), udt.getPhone(), udt.getEmail()));

        return entity;
    }
}