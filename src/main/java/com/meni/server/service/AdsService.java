package com.meni.server.service;

import com.meni.server.exception.AdNotFoundException;
import com.meni.server.model.AdDto;
import com.meni.server.model.RouteDto;
import com.meni.server.model.Status;
import com.meni.server.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class AdsService {
    @Autowired
    AdsRepository adRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    VolunteersRoutsRepository volunteersRoutsRepository;

    public void delete(long id) {
        if (!adRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        adRepository.deleteById(id);
    }

    public List<AdDto> getAds() {
        return Ad.convertListAdsToListAdsDto((List<Ad>) adRepository.findAll());
    }

    public AdDto add(AdDto dto) {
        Ad ad = toEntity(dto);
        Ad updatedAd = adRepository.save(ad);
        AdDto adDto = Ad.convertAdToAdDTO(updatedAd);
        User user = handleUser(adDto.getUserId());
        user.addAd(ad);
        userRepository.save(user);
        return adDto;

    }

    public Ad getAdById(long id) {
        Optional<Ad> optionalAd = adRepository.findById(id);
        return optionalAd.orElseThrow(() -> new AdNotFoundException("Couldn't find a Ad with id: " + id));
    }

    private Ad toEntity(AdDto dto) {

        Ad entity = new Ad();
        entity.setRoute(toRequestedRoute(dto.getRoute()));
        entity.setDescription(dto.getDescription().toLowerCase());
        entity.setTitle(dto.getTitle().toLowerCase());
        entity.setUser(handleUser(dto.getUserId()));
        return entity;
    }

    private RequestedRoute toRequestedRoute(RouteDto dto) {
        RequestedRoute entity = new RequestedRoute();
        entity.setFromLocation(dto.getFromLocation().toLowerCase());
        entity.setToLocation(dto.getToLocation().toLowerCase());
        entity.setExitTime(dto.getExitTime());
        entity.setArrivalTime(dto.getArrivalTime());
        return entity;
    }

    public void updateStatus(long ad_id, String status) throws EnumConstantNotPresentException {
        try {
            Ad ad = getAdById(ad_id);
            switch (status) {
                case "MATCH_FOUND":
                    ad.setStatus(Status.MATCH_FOUND);
                    break;
                case "RESOLVED":
                    ad.setStatus(Status.RESOLVED);
                    break;
                case "PENDING":
                    ad.setStatus(Status.PENDING);
                    break;
                default:
                    throw new EnumConstantNotPresentException(Status.class, status);

            }
            adRepository.save(ad);
        } catch (AdNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
    }

    public List<AdDto> getSortedAds(String sort, int limit, long id) {

        List<Ad> list = handleUser(id).getAds();

        if (sort.toLowerCase().equals("desc")) {
            list.sort(Comparator.comparing(Ad::getUpdateDateTime));
        } else if (sort.toLowerCase().equals("asc")) {
            list.sort((ad1, ad2) -> ad2.getUpdateDateTime().compareTo(ad1.getUpdateDateTime()));
        }

        return Ad.convertListAdsToListAdsDto(list.subList(0, Math.min(list.size(), limit)));

    }

    private User handleUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        return optionalUser.get();
    }

    public void matchRequestedRoutesWithVolunteerRoutes() {
        List<Ad> allAds = adRepository.findByStatus(Status.PENDING);

        allAds.forEach(ad -> {
            RequestedRoute requestedRoutes = ad.getRoute();
            List<VolunteerRoute> matchRoutes = volunteersRoutsRepository.findByFromLocationAndToLocation(
                    requestedRoutes.getFromLocation(),
                    requestedRoutes.getToLocation());

            findVolunteer(ad, matchRoutes);
        });

    }

    private void findVolunteer(Ad ad, List<VolunteerRoute> matchRoutes) {
        if (matchRoutes == null) {
            return;
        }

        matchRoutes.stream()
                .filter(volunteerRoute -> volunteerRoute.getUser().getId() != ad.getUser().getId())
                .findAny()
                .ifPresent(volunteerRoute -> {
                    ad.setVolunteerData(volunteerRoute.getUser());
                    ad.setStatus(Status.MATCH_FOUND);
                    adRepository.save(ad);
                });
    }

    public List<AdDto> getAdsByStatus(String status) {
        return Ad.convertListAdsToListAdsDto(adRepository.findByStatus(Status.valueOf(status)));
    }
}
