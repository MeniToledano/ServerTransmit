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

    public List<RouteDto> getRoutes() {
        return RequestedRoute.convertListOfRequestedRoutesToListRouteDto((List<RequestedRoute>) repository.findAll());
    }

    public RouteDto getAdById(long id) {
        Optional<RequestedRoute> optionalRoute = repository.findById(id);
        return RequestedRoute.convertRequestedRouteToRouteDto(optionalRoute.orElseThrow(() -> new RouteNotFoundException("Couldn't find a Route with id: " + id)));
    }

    private RequestedRoute toEntity(RouteDto dto) {

        RequestedRoute entity = new RequestedRoute();
        entity.setFromLocation(dto.getFromLocation());
        entity.setToLocation(dto.getToLocation());
        entity.setExitTime(dto.getExitTime());
        entity.setArrivalTime(dto.getArrivalTime());

        return entity;
    }

}
