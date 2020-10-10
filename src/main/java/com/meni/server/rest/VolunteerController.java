package com.meni.server.rest;

import com.meni.server.model.VolunteerDto;
import com.meni.server.repo.Volunteer;
import com.meni.server.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {
    @Autowired
    VolunteerService service;

    @GetMapping
    public List<Volunteer> getVolunteers() {
        return service.getVolunteers();
    }

    @PostMapping
    public void postVolunteer(@RequestBody VolunteerDto dto) {
        service.add(dto);
    }

    @GetMapping("/{id}")
    public Volunteer getById(@PathVariable(required = true) long id) {
        return service.getVolunteersById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        service.delete(id);
    }
}
