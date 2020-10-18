package com.meni.server.rest;

import com.meni.server.model.AdDto;
import com.meni.server.repo.Ad;
import com.meni.server.service.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class AdController {
    @Autowired
    AdsService service;

    @GetMapping("/ads")
    public Map<String , List<Ad>> getAds() {
        return Map.of("Ads",service.getAds());
    }

    @PostMapping("/ads")
    public Ad postAd(@RequestBody AdDto dto) {
        return service.add(dto);

    }

    @GetMapping("/user/{id}/ads")
    public Ad getById(@PathVariable(required = true) long id) {
        return service.getAdById(id);
    }

    @DeleteMapping("/user/{id}/ads/{ad_id}")
    public void delete(@PathVariable(required = true) long ad_id) { service.delete(ad_id);  }
}