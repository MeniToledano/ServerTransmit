package com.meni.server.service;

import com.meni.server.exception.RouteNotFoundException;
import com.meni.server.model.RouteDto;
import com.meni.server.repo.RequestedRoute;
import com.meni.server.repo.VolunteerRoute;
import com.meni.server.repo.VolunteersRoutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VolunteersRoutesService {
    @Autowired
    VolunteersRoutsRepository repository;


    public void add(RouteDto dto) {
        repository.save(toEntity(dto));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<VolunteerRoute> getRoutes() {
        return (List<VolunteerRoute>) repository.findAll();
    }

    public VolunteerRoute getAdById(long id) {
        Optional<VolunteerRoute> optionalRoute = repository.findById(id);
        return optionalRoute.orElseThrow(() -> new RouteNotFoundException("Couldn't find a Route with id: " + id));
    }

    private VolunteerRoute toEntity(RouteDto dto) {

        VolunteerRoute entity = new VolunteerRoute(dto);
        System.out.println("");

        return entity;
    }

}
