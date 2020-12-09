package com.meni.server.repo;

import com.meni.server.model.EStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdsRepository extends CrudRepository<Ad, Long> {

    List<Ad> findByStatus(EStatus status);
}

