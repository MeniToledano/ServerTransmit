package com.meni.server.service;

import com.meni.server.exception.RouteNotFoundException;
import com.meni.server.model.RouteDto;
import com.meni.server.model.UserDto;
import com.meni.server.repo.User;
import com.meni.server.repo.UserRepository;
import com.meni.server.repo.VolunteerRoute;
import com.meni.server.repo.VolunteersRoutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class VolunteersRoutesService {
    @Autowired
    VolunteersRoutsRepository repository;
    @Autowired
    UserRepository userRepo;

    public List<RouteDto> add(long userID, RouteDto[] dto) {
        return VolunteerRoute.convertListOfVolunteerRoutesToListRouteDto(toEntity(userID, dto));
    }

    public List<RouteDto> delete(long id) {
        Optional<VolunteerRoute> optionalVolunteerRoute= repository.findById(id);
        VolunteerRoute volunteerRoute = optionalVolunteerRoute.get();

        User user = volunteerRoute.getUser();
        List<VolunteerRoute> volunteerRoutes = user.getRoutes();
        volunteerRoutes.remove(volunteerRoute);
        repository.deleteById(id);
        return VolunteerRoute.convertListOfVolunteerRoutesToListRouteDto(volunteerRoutes);
    }

    public List<RouteDto> getRoutes() {
        return convertVolunteerRoutesListToRouteDTOList((List<VolunteerRoute>) repository.findAll());
    }

    private List<RouteDto> convertVolunteerRoutesListToRouteDTOList(List<VolunteerRoute> list){
        List<RouteDto> convertedList = new LinkedList<>();
        for(VolunteerRoute route : list){
            convertedList.add(VolunteerRoute.convertVolunteerRouteToRouteDto(route));
        }
        return convertedList;
    }

    public RouteDto getAdById(long id) {
        Optional<VolunteerRoute> optionalRoute = repository.findById(id);
        return VolunteerRoute.convertVolunteerRouteToRouteDto(optionalRoute.orElseThrow(() -> new RouteNotFoundException("Couldn't find a Route with id: " + id)));
    }

    private List<VolunteerRoute> toEntity(long userID, RouteDto[] dto) {

        List<VolunteerRoute> listOfRoutes = new LinkedList<>();
        Optional<User> optionalUser = userRepo.findById(userID);
        User user = optionalUser.get();
        repository.deleteAll();
        for(RouteDto r: dto) {
            VolunteerRoute entity = new VolunteerRoute(r);
            entity.setUser(user);
            repository.save(entity);
            listOfRoutes.add(entity);
        }
        user.addRoutes(listOfRoutes);
        return listOfRoutes;
    }

    public Map<String, List<UserDto>> getUserByRoute_FromLocation(String fromLocation) {
        List<VolunteerRoute> matchRoutes = repository.findByFromLocation(fromLocation);
        List<User> matchUsers = new LinkedList<>();
        for( VolunteerRoute vr : matchRoutes){
            matchUsers.add(vr.getUser());
        }
        return Map.of("volunteer user data:",User.convertListUsersToListUsersDto(matchUsers));
    }
}
