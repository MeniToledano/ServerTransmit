package com.meni.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Transactional
    Optional<User> findByUserNameAndPassword(String userName, String password);

    @Transactional
    User findByUserName(String userName);


    @Transactional
    Boolean existsByUserName(String username);

    @Transactional
    Boolean existsByEmail(String email);
}
