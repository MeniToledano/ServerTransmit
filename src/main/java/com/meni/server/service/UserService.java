package com.meni.server.service;

import com.meni.server.exception.UserAlreadyExistAuthenticationException;
import com.meni.server.model.LoginDto;
import com.meni.server.model.UserDto;
import com.meni.server.repo.User;
import com.meni.server.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDto add(UserDto dto) {

        if (userRepository.findByUserName(dto.getUserName()) == null){
            return User.convertUserToUserDto(userRepository.save(toEntity(dto)));
        }else{
            return null;
        }
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public List<UserDto> getUsers() {
        return User.convertListUsersToListUsersDto((List<User>) userRepository.findAll());
    }

    public UserDto getUserById(long id) {
        User user = handleUser(id);
        return User.convertUserToUserDto(user);
    }

    private User toEntity(UserDto userDto) {
        User entity = new User();
        entity.setName(userDto.getFirstName());
        entity.setLastName(userDto.getLastName());
        entity.setPhone(userDto.getPhone());
        entity.setEMail(userDto.getEmail());
        entity.setUserName(userDto.getUserName());
        entity.setPassword(userDto.getPassword());
        return entity;
    }

    public UserDto update(long userId, UserDto userDto) {
        User user = handleUser(userId);
        updateUser(user, userDto);
        return User.convertUserToUserDto(user);
    }

    private void updateUser(User user, UserDto userDto) {
        if (userDto.getEmail() != null) {
            user.setEMail(userDto.getEmail());
        }
        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getFirstName() != null) {
            user.setName(userDto.getFirstName());
        }
        if (userDto.getPhone() != null) {
            user.setPhone(user.getPhone());
        }
        userRepository.save(user);
    }

    private User handleUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        return optionalUser.get();
    }

    public UserDto getUserByLogin(LoginDto dto) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUserNameAndPassword(dto.getUserName(), dto.getPassword()));
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        return User.convertUserToUserDto(optionalUser.get());
    }
}
