package com.meni.server.rest;

import com.meni.server.model.UserDto;
import com.meni.server.repo.User;
import com.meni.server.service.UserService;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.persistence.PostUpdate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<UserDto> getUsers() {
        return service.getUsers();
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Map<String,UserDto> postUser(@RequestBody UserDto dto) {
        if (dto.getFirstName() == null) {
            return Map.of("User",service.getUserByLogin(dto));
        }else{
            return  Map.of("User",service.add(dto));
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public UserDto updateUser(@PathVariable(required = true) long id, @RequestBody UserDto dto){ return service.update(id, dto); }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable(required = true) long id) { return service.getUserById(id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) { service.delete(id); }
}
