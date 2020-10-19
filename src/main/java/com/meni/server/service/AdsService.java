package com.meni.server.service;

import com.meni.server.exception.AdNotFoundException;
import com.meni.server.exception.RouteNotFoundException;
import com.meni.server.exception.StatusNotValidException;
import com.meni.server.model.AdDto;
import com.meni.server.model.RouteDto;
import com.meni.server.model.StatusDTO;
import com.meni.server.repo.*;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class AdsService {
    @Autowired
    AdsRepository adRepository;
    @Autowired
    UserRepository userRepository;

    public Ad add(AdDto dto) {
        Ad ad = toEntity(dto);
        return adRepository.save(ad);

    }

    public void delete(long id) {
        adRepository.deleteById(id);
    }

    public List<Ad> getAds() {
        return (List<Ad>) adRepository.findAll();
    }

    public Ad getAdById(long id) {
        Optional<Ad> optionalAd = adRepository.findById(id);
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

    public void updateStatus(long ad_id, StatusDTO status){
        Ad ad = getAdById(ad_id);
        checkStatus(status.getStatus());
        ad.setStatus(status.getStatus());
        adRepository.save(ad);
    }
    private void checkStatus(String status){
        String lowerStatus = status.toLowerCase();
        List<String> validStatus = new LinkedList<>();
        validStatus.add("resolved");
        validStatus.add("match-found");
        validStatus.add("pending");
        if(!validStatus.contains(lowerStatus)){
            throw new StatusNotValidException();
        }

    }
}