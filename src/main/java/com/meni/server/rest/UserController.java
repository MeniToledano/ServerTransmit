package com.meni.server.rest;
import com.meni.server.model.UserDto;
import com.meni.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public List<UserDto> getUsers() {
        return service.getUsers();
    }

    @PostMapping
    public Map<String,UserDto> postUser(@RequestBody UserDto dto) {
        if (dto.getFirstName() == null) {
            return Map.of("user",service.getUserByLogin(dto));
        }else{
            return  Map.of("user",service.add(dto));
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public UserDto updateUser(@PathVariable(required = true) long id, @RequestBody UserDto dto){ return service.update(id, dto); }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable(required = true) long id) { return service.getUserById(id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) { service.delete(id); }
}
