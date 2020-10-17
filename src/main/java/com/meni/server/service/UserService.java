package com.meni.server.service;

import com.meni.server.exception.UserNotFoundException;
import com.meni.server.model.UserDto;
import com.meni.server.repo.User;
import com.meni.server.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    UserRepository repository;

    public User add(UserDto dto) {

        User user = repository.save(toEntity(dto));
        System.out.println("as");
        return user;
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<User> getUsers() {
        return (List<User>) repository.findAll();
    }

    public User getUserById(long id) {
        Optional<User> optionalUser = repository.findById(id);
        return optionalUser.orElseThrow(() -> new UserNotFoundException("Couldn't find a User with id: " + id));
    }

    private User toEntity(UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getLastName(), userDto.getPhone(), userDto.getEmail());
        return user;
    }

    public User update(long userId, UserDto userDto){
        Optional<User> optionalUser = repository.findById(userId);
        if (optionalUser != null){
            User user = optionalUser.get();
            updateUser(user ,userDto);
        }

        Optional<User> optionalUser2= repository.findById(userId);
        return optionalUser2.orElseThrow(() -> new UserNotFoundException("Couldn't find a User with id: " + userId));
    }

    private void updateUser(User user, UserDto userDto) {
        if(userDto.getEmail() != null){ user.seteMail(userDto.getEmail()); }
        if(userDto.getLastName() != null){user.setLastName(userDto.getLastName()) ;}
        if(userDto.getName() != null){ user.setName(userDto.getName()) ;}
        if(userDto.getPhone() != null){user.setPhone(user.getPhone()) ;}
        repository.save(user);
    }

}
