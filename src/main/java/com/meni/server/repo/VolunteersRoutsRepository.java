package com.meni.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface VolunteersRoutsRepository extends CrudRepository<VolunteerRoute, Long> {

    @Transactional
    Long deleteByUser(User user);
    List<VolunteerRoute> findByFromLocationAndToLocation(String fromLocation, String ToLocation);
}

