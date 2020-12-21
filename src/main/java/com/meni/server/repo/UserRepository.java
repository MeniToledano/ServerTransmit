package com.meni.server.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Transactional
    User findByUserNameAndPassword(String userName, String password);

    @Transactional
    User findByUserName(String userName);
}
