package com.meni.server.rest;

import com.meni.server.model.DogDto;
import com.meni.server.repo.Dog;
import com.meni.server.service.DogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogsController {
    @Autowired
    DogsService service;

    @GetMapping
    public List<Dog> getDogs() {
        return service.getDogs();
    }

    @PostMapping
    public void postDogs(@RequestBody DogDto dto) {
        service.add(dto);
    }

    @GetMapping("/{id}")
    public Dog getById(@PathVariable(required = true) long id) {
        return service.getDogById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        service.delete(id);
    }
}