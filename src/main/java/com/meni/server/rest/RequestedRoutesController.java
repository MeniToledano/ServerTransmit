package com.meni.server.rest;

import com.meni.server.model.RouteDto;
import com.meni.server.service.RequestedRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requestRoutes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RequestedRoutesController {
    @Autowired
    RequestedRoutesService service;

    @GetMapping
    public List<RouteDto> getRoutes() {
        return service.getRoutes();
    }

    @GetMapping("/{id}")
    public RouteDto getById(@PathVariable(required = true) long id) {
        return service.getAdById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) String id) {
        service.delete(id);
    }

}
