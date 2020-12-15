package com.meni.server.rest;

import com.meni.server.model.AdDto;
import com.meni.server.model.StatusStringDTO;
import com.meni.server.service.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdController {
    @Autowired
    AdsService service;

    @GetMapping("/ads/{status}")
    public Map<String, List<AdDto>> getAds(@PathVariable(required = false) String status) {
        if (status != null) {
            return Map.of("ads", service.getAdsByStatus(status));
        } else {
            return Map.of("ads", service.getAds());
        }
    }

    @PostMapping("/ads")
    public Map<String, AdDto> postAd(@RequestBody AdDto dto) {
        return Map.of("ad", service.add(dto));
    }

    @GetMapping(value = "/user/{id}/ads")
    public Map<String, List<AdDto>> getSortedAds(@RequestParam(required = false, defaultValue = "desc") String sort,
                                                 @RequestParam(required = false, defaultValue = "10") String limit,
                                                 @PathVariable long id) {

        if (!sort.equals("desc") && !sort.equals("asc")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sort not supported");
        }
        return Map.of("ads", service.getSortedAds(sort, Integer.parseInt(limit), id));
    }

    @DeleteMapping("/ads/{ad_id}")
    public void delete(@PathVariable long ad_id) {
        service.delete(ad_id);
    }

    @PostMapping(value = "/ads/{ad_id}/status", produces = "application/json")
    public void postAdStatus(@RequestBody StatusStringDTO status,
                             @PathVariable long ad_id) {
        service.updateStatus(ad_id, status.getStatus());
    }


}