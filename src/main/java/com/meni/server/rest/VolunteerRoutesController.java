package com.meni.server.rest;

import com.meni.server.repo.RequestedRoute;
import com.meni.server.repo.VolunteerRoute;
import com.meni.server.service.VolunteersRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/volunteerRoutes")
public class VolunteerRoutesController {
    @Autowired
    VolunteersRoutesService service;


    @GetMapping
    public List<VolunteerRoute> getRoutes() {
        return service.getRoutes();
    }

//    @PostMapping
//    public void postAd(@RequestBody RouteDto dto) {
//        service.add(dto);
//    }

    @GetMapping("/{id}")
    public VolunteerRoute getById(@PathVariable(required = true) long id) {
        return service.getAdById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        service.delete(id);
    }

}
