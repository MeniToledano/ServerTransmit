package com.meni.server.rest;

import com.meni.server.model.AdDto;
import com.meni.server.model.StatusDTO;
import com.meni.server.repo.Ad;
import com.meni.server.service.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class AdController {
    @Autowired
    AdsService service;

    @GetMapping("/ads")
    public Map<String , List<AdDto>> getAds() {
        return Map.of("Ads",service.getAds());
    }

    @PostMapping("/ads")
    public AdDto postAd(@RequestBody AdDto dto) { return service.add(dto); }

    @GetMapping(value = "/user/{id}/ads")
    public List<AdDto> getAdsByUserId(@PathVariable long id) { return service.getUserAds(id); }

    @GetMapping(value = "/user/{id}/ads", params = {"sort","limit"})
    public List<AdDto> getSortedAds(@RequestParam(value = "sort",required = false) String sort,
                                    @RequestParam(value = "limit",required = false) String limit,
                            @PathVariable long id) {
        return service.getSortedAds(sort, 5, id);
    }


    @GetMapping("/user/id/ads/{ad_id}")
    public void delete(@PathVariable long ad_id) { service.getAdById(ad_id);  }

    @DeleteMapping("/user/{id}/ads/{ad_id}")
    public void delete(@PathVariable long ad_id,@PathVariable long id) { service.delete(ad_id);  }

    @PostMapping("/user/{id}/ads/{ad_id}/status")
    public void postAdStatus(@RequestBody(required = true) StatusDTO status,
                           @PathVariable(required = true) long ad_id) {
        service.updateStatus(ad_id, status);

    }

}