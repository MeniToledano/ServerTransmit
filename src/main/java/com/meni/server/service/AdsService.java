package com.meni.server.service;

import com.meni.server.exception.AdNotFoundException;
import com.meni.server.exception.StatusNotValidException;
import com.meni.server.model.AdDto;
import com.meni.server.model.RouteDto;
import com.meni.server.model.StatusDTO;
import com.meni.server.model.UserDto;
import com.meni.server.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class AdsService {
    @Autowired
    AdsRepository adRepository;
    @Autowired
    UserRepository userRepository;



    public void delete(long id) {
        adRepository.deleteById(id);
    }

    public List<AdDto> getAds() {
        return convertListAdsToListAdsDto((List<Ad>)adRepository.findAll());
    }

    public AdDto add(AdDto dto) {
        Ad ad = toEntity(dto);
        Ad updatedAd = adRepository.save(ad);
        AdDto adDto= convertAdToAdDTO(updatedAd);
        System.out.println("a");
        Optional<User> optionalUser = userRepository.findById(adDto.getUser_id());
        System.out.println("v");
        User user = optionalUser.get();
        user.addAd(ad);
        userRepository.save(user);
        return adDto;

    }
    private AdDto convertAdToAdDTO(Ad ad){
        AdDto adDto = new AdDto();
        adDto.setDate(ad.getDate());
        adDto.setDescription(ad.getDescription());
        adDto.setId(ad.getId());
        adDto.setStatus(ad.getStatus());
        adDto.setTitle(ad.getTitle());
        adDto.setUser_id(ad.getUser().getId());

        UserDto user = new UserDto();
        user.setUserId(ad.getUser().getId());
        user.setPhone(ad.getUser().getPhone());
        user.setName(ad.getUser().getName());
        user.setLastName(ad.getUser().getLastName());
        user.setEmail(ad.getUser().getEMail());

       adDto.setUser(user);

        RouteDto route = new RouteDto();
        route.setFromLocation(ad.getRoute().getFromLocation());
        route.setToLocation(ad.getRoute().getToLocation());
        route.setExitTime(ad.getRoute().getExitTime());
        route.setArrivalTime(ad.getRoute().getArrivalTime());
        adDto.setRoute(route);

        return adDto;
    }

    private List<AdDto> convertListAdsToListAdsDto(List<Ad> ads){
        List<AdDto> adsDTO = new LinkedList<>();
        for (Ad ad : ads){
            adsDTO.add(convertAdToAdDTO(ad));
        }
        return adsDTO;
    }

    public List<AdDto> getUserAds(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        return convertListAdsToListAdsDto(user.getAds());
    }

    public Ad getAdById(long id) {
        Optional<Ad> optionalAd = adRepository.findById(id);
        return optionalAd.orElseThrow(() -> new AdNotFoundException("Couldn't find a Ad with id: " + id));
    }

    private Ad toEntity(AdDto dto) {

        Ad entity = new Ad();
        long userId = dto.getUser_id();
        RouteDto routeDto = dto.getRoute();
        entity.setRoute(new RequestedRoute(
                routeDto.getFromLocation(),
                routeDto.getToLocation(),
                routeDto.getExitTime(),
                routeDto.getArrivalTime()));
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();

        entity.setUser(user);
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());

     //   user.addAd(entity);
     //   userRepository.save(user); //here is the problem
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

    public List<AdDto> getSortedAds(String sort, int limit, long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        List<Ad> list = user.getAds();
        Collections.sort(list);
        return convertListAdsToListAdsDto(list.subList(0, Math.min(list.size(), limit)));

    }
}