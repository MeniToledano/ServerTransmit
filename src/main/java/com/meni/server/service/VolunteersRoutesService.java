package com.meni.server.service;

import com.meni.server.exception.RouteNotFoundException;
import com.meni.server.model.RouteDto;
import com.meni.server.repo.User;
import com.meni.server.repo.UserRepository;
import com.meni.server.repo.VolunteerRoute;
import com.meni.server.repo.VolunteersRoutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class VolunteersRoutesService {
    @Autowired
    VolunteersRoutsRepository repository;
    @Autowired
    UserRepository userRepo;


    public List<VolunteerRoute> add(long userID, RouteDto[] dto) {
        List<VolunteerRoute> a = toEntity(userID, dto);
        return a;
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<VolunteerRoute> getRoutes() {
        return (List<VolunteerRoute>) repository.findAll();
    }

    public VolunteerRoute getAdById(long id) {
        Optional<VolunteerRoute> optionalRoute = repository.findById(id);
        return optionalRoute.orElseThrow(() -> new RouteNotFoundException("Couldn't find a Route with id: " + id));
    }

    private List<VolunteerRoute> toEntity(long userID, RouteDto[] dto) {

        List<VolunteerRoute> listOfRoutes = new LinkedList<>();
        Optional<User> optionalUser = userRepo.findById(userID);
        User user = optionalUser.get();
        for(RouteDto r: dto) {
            VolunteerRoute entity = new VolunteerRoute(r);
            entity.setUser(user);
            repository.save(entity);
            listOfRoutes.add(entity);
        }


        user.addRoutes(listOfRoutes);


        return listOfRoutes;
    }

}
