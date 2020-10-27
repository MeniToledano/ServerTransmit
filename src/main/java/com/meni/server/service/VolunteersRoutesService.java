package com.meni.server.service;

import com.meni.server.exception.RouteNotFoundException;
import com.meni.server.model.RouteDto;
import com.meni.server.model.UserDto;
import com.meni.server.repo.User;
import com.meni.server.repo.UserRepository;
import com.meni.server.repo.VolunteerRoute;
import com.meni.server.repo.VolunteersRoutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class VolunteersRoutesService {
    @Autowired
    VolunteersRoutsRepository volunteerRoutesRepository;
    @Autowired
    UserRepository userRepository;

    public List<RouteDto> add(long userID, RouteDto[] dto) {
        return VolunteerRoute.convertListOfVolunteerRoutesToListRouteDto(toEntity(userID, dto));
    }

    public List<RouteDto> delete(long id) {
        VolunteerRoute volunteerRoute = handleVolunteerRoutes(id);

        User user = volunteerRoute.getUser();
        List<VolunteerRoute> volunteerRoutes = user.getRoutes();
        volunteerRoutes.remove(volunteerRoute);
        volunteerRoutesRepository.deleteById(id);
        return VolunteerRoute.convertListOfVolunteerRoutesToListRouteDto(volunteerRoutes);
    }

    private VolunteerRoute handleVolunteerRoutes(long id) {
        Optional<VolunteerRoute> optionalVolunteerRoute= volunteerRoutesRepository.findById(id);
        if(optionalVolunteerRoute.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"Unable to find resource");
        }
        return optionalVolunteerRoute.get();
    }

    public List<RouteDto> getRoutes(long userId) {
        User user = handleUser(userId);
        return VolunteerRoute.convertListOfVolunteerRoutesToListRouteDto(user.getRoutes());
    }

    public RouteDto getAdById(long id) {
        Optional<VolunteerRoute> optionalRoute = volunteerRoutesRepository.findById(id);
        return VolunteerRoute.convertVolunteerRouteToRouteDto(optionalRoute.orElseThrow(() -> new RouteNotFoundException("Couldn't find a Route with id: " + id)));
    }

    //all new router associated with the user will be REPLACED!
    private List<VolunteerRoute> toEntity(long userID, RouteDto[] dto) {

        List<VolunteerRoute> listOfRoutes = new LinkedList<>();
        User user =handleUser(userID); //checks if user exist
        volunteerRoutesRepository.deleteByUser(user); //delete user routes

        for(RouteDto routeDto: dto) {
            VolunteerRoute entity = toVolunteerRoute(routeDto);
            entity.setUser(user); //associate each route with user
            volunteerRoutesRepository.save(entity);
            listOfRoutes.add(entity);
        }
        user.addRoutes(listOfRoutes); //maybe this line does nothing
        return listOfRoutes;
    }

    private User handleUser(long userID) {
        Optional<User> optionalUser = userRepository.findById(userID);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"Unable to find resource");
        }
        return optionalUser.get();
    }

    private VolunteerRoute toVolunteerRoute(RouteDto routeDto) {
        VolunteerRoute volunteerRoute = new VolunteerRoute();
        volunteerRoute.setFromLocation(routeDto.getFromLocation().toLowerCase());
        volunteerRoute.setToLocation(routeDto.getToLocation().toLowerCase());
        volunteerRoute.setExitTime(routeDto.getExitTime().toLowerCase());
        volunteerRoute.setArrivalTime(routeDto.getArrivalTime().toLowerCase());
        return volunteerRoute;
    }

    public Map<String, List<UserDto>> getUserByRoute_FromLocationAndToLocation(String fromLocation, String toLocation) {
        List<VolunteerRoute> matchRoutes = volunteerRoutesRepository.findByFromLocationAndToLocation(fromLocation, toLocation);
        List<User> matchUsers = new LinkedList<>();
        for( VolunteerRoute vr : matchRoutes){
            matchUsers.add(vr.getUser());
        }
        return Map.of("volunteers:",User.convertListUsersToListUsersDto(matchUsers));
    }
}
