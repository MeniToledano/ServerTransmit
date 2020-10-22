package com.meni.server.service;

import com.meni.server.exception.UserNotFoundException;
import com.meni.server.model.AdDto;
import com.meni.server.model.RouteDto;
import com.meni.server.model.UserDto;
import com.meni.server.repo.Ad;
import com.meni.server.repo.User;
import com.meni.server.repo.UserRepository;
import com.meni.server.repo.VolunteerRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    UserRepository repository;

    public UserDto add(UserDto dto) { return convertUserToUserDto(repository.save(toEntity(dto)));  }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<UserDto> getUsers() {
        return convertListUsersToListUsersDto((List<User>) repository.findAll());
    }

    public UserDto getUserById(long id) {
        Optional<User> optionalUser = repository.findById(id);
        return convertUserToUserDto(optionalUser.get());
    }

    private User toEntity(UserDto userDto) { return new User(userDto.getName(), userDto.getLastName(), userDto.getPhone(), userDto.getEmail()); }

    public UserDto update(long userId, UserDto userDto){
        Optional<User> optionalUser = repository.findById(userId);
        User user = optionalUser.get();
        updateUser(user ,userDto);
        return convertUserToUserDto(user);
    }

    private void updateUser(User user, UserDto userDto) {
        if(userDto.getEmail() != null){ user.setEMail(userDto.getEmail()); }
        if(userDto.getLastName() != null){user.setLastName(userDto.getLastName()) ;}
        if(userDto.getName() != null){ user.setName(userDto.getName()) ;}
        if(userDto.getPhone() != null){user.setPhone(user.getPhone()) ;}
        repository.save(user);
    }

    private List<UserDto> convertListUsersToListUsersDto(List<User> list){
        List<UserDto> newList = new LinkedList<>();
        for (User user : list){
            newList.add(convertUserToUserDto(user));
        }
        return newList;
    }

    private UserDto convertUserToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEMail());
        userDto.setLastName(user.getLastName());
        userDto.setName(user.getName());
        userDto.setPhone(user.getPhone());
        userDto.setUserId(user.getId());
        if(user.getAds() != null) {
            userDto.setAds(convertListAdsToListAdsDto(user.getAds()));
        }
        if(user.getRoutes() != null) {
            userDto.setRoutes(convertListRoutesToListRoutesDto(user.getRoutes()));
        }
        return userDto;
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

    private List<RouteDto> convertListRoutesToListRoutesDto(List<VolunteerRoute> list){
        List<RouteDto> newList = new LinkedList<>();
        for (VolunteerRoute route : list){
            newList.add(convertVolunteerRouteToRouteDto(route));
        }
        return newList;
    }

    private RouteDto convertVolunteerRouteToRouteDto(VolunteerRoute route){
        RouteDto routeDto = new RouteDto();
        routeDto.setArrivalTime(route.getArrivalTime());
        routeDto.setFromLocation(route.getFromLocation());
        routeDto.setToLocation(route.getToLocation());
        routeDto.setExitTime(route.getExitTime());
        routeDto.setUser(route.getUser());
        routeDto.setId(route.getId());
        return routeDto;
    }

}
