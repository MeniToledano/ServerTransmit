package com.meni.server.rest;

import com.meni.server.model.RouteDto;
import com.meni.server.model.UserDto;
import com.meni.server.service.VolunteersRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/{id}/routes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VolunteerRoutesController {
    @Autowired
    VolunteersRoutesService routesService;

    @GetMapping
    public Map<String, List<RouteDto>> getRoutes(@PathVariable("id") long userId) {
        return Map.of("routes", routesService.getRoutes(userId));
    }

    @PostMapping
    public Map<String, List<RouteDto>> postUserRoutes(@PathVariable("id") long userId, @RequestBody RouteDto[] routes) {
        return Map.of("routes", routesService.add(userId, routes));
    }

    @GetMapping("/fromLocation:{fromLocation}/toLocation:{toLocation}")
    public Map<String, List<UserDto>> getByFromLocation(@PathVariable(required = true) String fromLocation
            , @PathVariable(required = true) String toLocation) {
        return routesService.getUserByRoute_FromLocationAndToLocation(fromLocation, toLocation);
    }

    @GetMapping("/{id}")
    public RouteDto getById(@PathVariable(required = true) long id) {
        return routesService.getAdById(id);
    }

    @DeleteMapping("/{id}")
    public Map<String, List<RouteDto>> delete(@PathVariable(required = true) long id) {
        return Map.of("routes:", routesService.delete(id));
    }

}
