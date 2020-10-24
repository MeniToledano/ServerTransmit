package com.meni.server.service;

import com.meni.server.exception.AdNotFoundException;
import com.meni.server.exception.StatusNotValidException;
import com.meni.server.model.AdDto;
import com.meni.server.model.RouteDto;
import com.meni.server.model.StatusDTO;
import com.meni.server.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AdsService {
    @Autowired
    AdsRepository adRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    VolunteersRoutsRepository volunteersRoutsRepository;

    public void delete(long id) {
        adRepository.deleteById(id);
    }

    public List<AdDto> getAds() {
        return Ad.convertListAdsToListAdsDto((List<Ad>)adRepository.findAll());
    }

    public AdDto add(AdDto dto) {
        Ad ad = toEntity(dto);
        Ad updatedAd = adRepository.save(ad);
        AdDto adDto= Ad.convertAdToAdDTO(updatedAd);
        Optional<User> optionalUser = userRepository.findById(adDto.getUser_id());
        User user = optionalUser.get();
        user.addAd(ad);
        userRepository.save(user);
        return adDto;

    }

    public List<AdDto> getUserAds(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        List<AdDto> asd = Ad.convertListAdsToListAdsDto(user.getAds());
        return asd;
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
        ad.setStatus(status.getStatus().toUpperCase());
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
        return Ad.convertListAdsToListAdsDto(list.subList(0, Math.min(list.size(), limit)));

    }

    public Map<String,String> matchRequestedRoutesWithVolunteerRoutes() {
        List<Ad> allAds = (List<Ad>) adRepository.findAll();
        List<VolunteerRoute> volunteerRoutes = (List<VolunteerRoute>) volunteersRoutsRepository.findAll();
        HashMap<String, String> matches = new HashMap<>();

        for (Ad ad : allAds) {
            RequestedRoute requestedRoutes = ad.getRoute();
            List<VolunteerRoute> matchRoutes = volunteersRoutsRepository.findByFromLocation(requestedRoutes.getFromLocation());
            if (matchRoutes != null) {
                for (VolunteerRoute volunteerRoute: matchRoutes){
                    matches.put("Ad id: "+ad.getId().toString(), "Volunteer user id: "+Long.toString(volunteerRoute.getId()));
                }
            }
        }
        return matches;
    }
}
