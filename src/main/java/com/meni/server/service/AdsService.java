package com.meni.server.service;

import com.meni.server.exception.AdNotFoundException;
import com.meni.server.model.AdDto;
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
    UserRepository userRepository;

    public Ad add(AdDto dto) {
        Ad ad = toEntity(dto);
        return repository.save(ad);

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
        long userId = dto.getUser_id();
        RouteDto routeDto = dto.getRoute();
        entity.setRoute(new RequestedRoute(routeDto.getFromLocation(), routeDto.getToLocation(), routeDto.getExitTime(), routeDto.getArrivalTime()));
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        entity.setUser(user);
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());

        return entity;
    }
}