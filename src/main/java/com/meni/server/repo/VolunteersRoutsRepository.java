package com.meni.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteersRoutsRepository extends CrudRepository<VolunteerRoute, Long> {

    List<VolunteerRoute> findByFromLocation(String fromLocation );
}

