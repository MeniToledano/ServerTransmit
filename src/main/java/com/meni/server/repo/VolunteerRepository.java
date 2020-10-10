package com.meni.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {
    List<Volunteer> findUserByRoutes_FromLocation(String fromLocation);

}

