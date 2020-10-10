package com.meni.server.rest;

import com.meni.server.repo.Volunteer;
import com.meni.server.repo.VolunteerRoute;
import com.meni.server.service.VolunteerService;
import com.meni.server.service.VolunteersRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/volunteers/routes")
public class VolunteerRoutesController {
    @Autowired
    VolunteersRoutesService routesService;
    @Autowired
    VolunteerService service;

    @GetMapping
    public List<VolunteerRoute> getRoutes() {
        return routesService.getRoutes();
    }

    @GetMapping("/from/{fromLocation}")
    public List<Volunteer> getByFromLocation(@PathVariable(required = true) String fromLocation) {
        return service.getVolunteersByRoute_FromLocation(fromLocation);
    }

    @GetMapping("/{id}")
    public VolunteerRoute getById(@PathVariable(required = true) long id) {
        return routesService.getAdById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        routesService.delete(id);
    }

}
