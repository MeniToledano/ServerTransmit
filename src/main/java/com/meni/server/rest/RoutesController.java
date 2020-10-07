package com.meni.server.rest;

import com.meni.server.model.AdDto;
import com.meni.server.model.RouteDto;
import com.meni.server.repo.Route;
import com.meni.server.service.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/routes")
public class RoutesController {
    @Autowired
    RoutesService service;

    @GetMapping
    public List<Route> getRoutes() {
        return service.getRoutes();
    }

//    @PostMapping
//    public void postAd(@RequestBody RouteDto dto) {
//        service.add(dto);
//    }

    @GetMapping("/{id}")
    public Route getById(@PathVariable(required = true) long id) {
        return service.getAdById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        service.delete(id);
    }


}
