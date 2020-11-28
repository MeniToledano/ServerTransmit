package com.meni.server.repo;

import com.meni.server.model.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Transactional
    User findByUserNameAndPassword(String userName, String password);

    @Transactional
    User findByUserName(String userName);
}
