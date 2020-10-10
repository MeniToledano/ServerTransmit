package com.meni.server.rest;

import com.meni.server.model.AdDto;
import com.meni.server.repo.Ad;
import com.meni.server.service.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdController {
    @Autowired
    AdsService service;

    @GetMapping
    public List<Ad> getAds() {
        return service.getAds();
    }

    @PostMapping
    public void postAd(@RequestBody AdDto dto) {
        service.add(dto);

    }

    @GetMapping("/{id}")
    public Ad getById(@PathVariable(required = true) long id) {
        return service.getAdById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        service.delete(id);
    }
}