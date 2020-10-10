package com.meni.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteersRoutsRepository extends CrudRepository<VolunteerRoute, Long> {
}

