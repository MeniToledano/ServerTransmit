package com.meni.server.service;

import com.meni.server.exception.VolunteerNotFoundException;
import com.meni.server.model.RouteDto;
import com.meni.server.model.UserDto;
import com.meni.server.model.VolunteerDto;
import com.meni.server.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class VolunteerService {
    @Autowired
    VolunteerRepository repository;
    @Autowired
    VolunteersRoutsRepository routesRepo;


    public void add(VolunteerDto dto) {
        Volunteer v = toEntity(dto);

        repository.save(v);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Volunteer> getVolunteers() {
        return (List<Volunteer>) repository.findAll();
    }

    public Volunteer getAdById(long id) {
        Optional<Volunteer> optionalRoute = repository.findById(id);
        return optionalRoute.orElseThrow(() -> new VolunteerNotFoundException("Couldn't find a Route with id: " + id));
    }

    private Volunteer toEntity(VolunteerDto dto) {

        Volunteer entity = new Volunteer();
        UserDto userDto = dto.getUser();
        User user = new User(userDto.getName(),userDto.getLastName(),userDto.getPhone(),userDto.getEmail());

        entity.setUser(user);
 //      entity.setRoutes(new RequestedRoute(dto.getRoutes()));
     //   dto.getRoutes().stream().forEach(routeDto -> System.out.println(routeDto));


        List<RouteDto> listOfRoutes = dto.getRoutes();
        List<VolunteerRoute> vr  = new LinkedList<>();
        for(RouteDto r: listOfRoutes){
            vr.add(new VolunteerRoute(r.getFromLocation(),r.getToLocation(),r.getExitTime(), r.getArrivalTime()));
        }
        entity.setRoutes(vr);

        for (VolunteerRoute volRoute: vr){
            volRoute.setVolunteer(entity);
        }
        return entity;

    }




}
