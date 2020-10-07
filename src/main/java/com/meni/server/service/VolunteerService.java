package com.meni.server.service;

import com.meni.server.exception.VolunteerNotFoundException;
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

        Volunteer entity = new Volunteer(dto);
        LinkedList<VolunteerRoute> listOfRoutes = entity.getRoutes();
        for(VolunteerRoute r: listOfRoutes){ //save volunteer each route
            r.setUserId(entity.getId());
            routesRepo.save(r);
        }
        return entity;
    }




}
