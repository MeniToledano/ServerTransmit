package com.meni.server.rest;

import com.meni.server.model.RouteDto;
import com.meni.server.model.UserDto;
import com.meni.server.repo.VolunteerRoute;
import com.meni.server.service.VolunteersRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/{id}/routes")
public class VolunteerRoutesController {
    @Autowired
    VolunteersRoutesService routesService;

    @GetMapping
    public Map<String, List<RouteDto>> getRoutes() {
        return Map.of("routes",routesService.getRoutes());
    }

    @PostMapping
    public Map<String, List<RouteDto>> postUserRoutes(@PathVariable("id") long userID , @RequestBody RouteDto[] routes) {
        return Map.of("routes",routesService.add(userID, routes));
    }

    @GetMapping("/fromLocation:{fromLocation}")
    public Map<String, List<UserDto>> getByFromLocation(@PathVariable(required = true) String fromLocation) {
        return routesService.getUserByRoute_FromLocation(fromLocation);
    }

    @GetMapping("/{id}")
    public RouteDto getById(@PathVariable(required = true) long id) {
        return routesService.getAdById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        routesService.delete(id);
    }

}
