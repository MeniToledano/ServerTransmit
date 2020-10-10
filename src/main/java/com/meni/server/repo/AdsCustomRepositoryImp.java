package com.meni.server.repo;

import com.meni.server.model.AdDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;

@Repository
public class AdsCustomRepositoryImp implements AdsRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<AdDto> findByuser(AdDto Ad) {
        System.out.println("h");
        //      Query query = entityManager.createNativeQuery("SELECT *");
        List<AdDto> listOfUser = new LinkedList<>();

        return listOfUser;
    }
}
