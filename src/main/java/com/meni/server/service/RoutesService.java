package com.meni.server.service;

import com.meni.server.exception.RouteNotFoundException;
import com.meni.server.model.RouteDto;
import com.meni.server.repo.Route;
import com.meni.server.repo.RoutesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoutesService {
    @Autowired
    RoutesRepository repository;

    public void add(RouteDto dto) {
        repository.save(toEntity(dto));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Route> getRoutes() {
        return (List<Route>) repository.findAll();
    }

    public Route getAdById(long id) {
        Optional<Route> optionalRoute = repository.findById(id);
        return optionalRoute.orElseThrow(() -> new RouteNotFoundException("Couldn't find a Route with id: " + id));
    }

    private Route toEntity(RouteDto dto) {

        Route entity = new Route(dto, 1);


        return entity;
    }

}
