package com.meni.server.service;

import com.meni.server.exception.RouteNotFoundException;
import com.meni.server.model.RouteDto;
import com.meni.server.repo.RequestedRoute;
import com.meni.server.repo.RequestedRoutesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RequestedRoutesService {
    @Autowired
    RequestedRoutesRepository repository;

    public void add(RouteDto dto) {
        repository.save(toEntity(dto));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<RequestedRoute> getRoutes() {
        return (List<RequestedRoute>) repository.findAll();
    }

    public RequestedRoute getAdById(long id) {
        Optional<RequestedRoute> optionalRoute = repository.findById(id);
        return optionalRoute.orElseThrow(() -> new RouteNotFoundException("Couldn't find a Route with id: " + id));
    }

    private RequestedRoute toEntity(RouteDto dto) {

        RequestedRoute entity = new RequestedRoute(dto.getFromLocation(), dto.getToLocation(), dto.getExitTime(), dto.getArrivalTime());


        return entity;
    }

}
