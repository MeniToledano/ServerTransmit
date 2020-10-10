package com.meni.server.repo;

import com.meni.server.model.AdDto;

import java.util.List;

public interface AdsRepositoryCustom {
    public List<AdDto> findByuser(AdDto Ad);
}
