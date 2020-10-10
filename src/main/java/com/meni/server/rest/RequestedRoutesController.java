package com.meni.server.rest;

import com.meni.server.repo.RequestedRoute;
import com.meni.server.service.RequestedRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requestRoutes")
public class RequestedRoutesController {
    @Autowired
    RequestedRoutesService service;

    @GetMapping
    public List<RequestedRoute> getRoutes() {
        return service.getRoutes();
    }

    @GetMapping("/{id}")
    public RequestedRoute getById(@PathVariable(required = true) long id) {
        return service.getAdById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        service.delete(id);
    }

}
