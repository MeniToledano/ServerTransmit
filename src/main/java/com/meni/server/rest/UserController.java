package com.meni.server.rest;

import com.meni.server.model.UserDto;
import com.meni.server.repo.User;
import com.meni.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public List<User> getUsers() {
        return service.getUsers();
    }

    @PostMapping
    public User postUser(@RequestBody UserDto dto) {
        User user = service.add(dto);
        return user;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public User updateUser(@PathVariable(required = true) long id, @RequestBody UserDto dto){
        User user = service.update(id, dto);
        return user;
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable(required = true) long id) {
        return service.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        service.delete(id);
    }
}
