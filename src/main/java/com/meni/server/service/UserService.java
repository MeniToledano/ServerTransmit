package com.meni.server.service;

import com.meni.server.exception.UserNotFoundException;
import com.meni.server.model.AdDto;
import com.meni.server.model.RouteDto;
import com.meni.server.model.UserDto;
import com.meni.server.repo.Ad;
import com.meni.server.repo.User;
import com.meni.server.repo.UserRepository;
import com.meni.server.repo.VolunteerRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    UserRepository repository;

    public UserDto add(UserDto dto) { return User.convertUserToUserDto(repository.save(toEntity(dto)));  }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<UserDto> getUsers() {
        return User.convertListUsersToListUsersDto((List<User>) repository.findAll());
    }

    public UserDto getUserById(long id) {
        Optional<User> optionalUser = repository.findById(id);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"Unable to find resource");
        }
        return User.convertUserToUserDto(optionalUser.get());
    }

    private User toEntity(UserDto userDto) { return new User(userDto.getName(), userDto.getLastName(), userDto.getPhone(), userDto.getEmail()); }

    public UserDto update(long userId, UserDto userDto){
        Optional<User> optionalUser = repository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"Unable to find resource");
        }
        User user = optionalUser.get();
        updateUser(user ,userDto);
        return User.convertUserToUserDto(user);
    }

    private void updateUser(User user, UserDto userDto) {
        if(userDto.getEmail() != null){ user.setEMail(userDto.getEmail()); }
        if(userDto.getLastName() != null){user.setLastName(userDto.getLastName()) ;}
        if(userDto.getName() != null){ user.setName(userDto.getName()) ;}
        if(userDto.getPhone() != null){user.setPhone(user.getPhone()) ;}
        repository.save(user);
    }


}
