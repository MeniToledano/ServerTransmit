package com.meni.server.repo;

import java.util.Optional;

import com.meni.server.model.ERole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    @Transactional
        Optional<Role> findByName(ERole name);
}
