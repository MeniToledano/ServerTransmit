package com.meni.server.rest;

import com.fasterxml.jackson.databind.node.TextNode;
import com.meni.server.model.AdDto;
import com.meni.server.model.Status;
import com.meni.server.model.StatusStringDTO;
import com.meni.server.service.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdController {
    @Autowired
    AdsService service;

    @GetMapping("/ads")
    public Map<String , List<AdDto>> getAds() {
        return Map.of("ads",service.getAds());
    }

    @PostMapping("/ads")
    public Map<String,AdDto> postAd(@RequestBody AdDto dto) {
        return Map.of("ad",service.add(dto));
    }

    @GetMapping(value = "/user/{id}/ads")
    public Map<String,List<AdDto>> getSortedAds(@RequestParam(required = false) String sort,
                                                @RequestParam(required = false) String limit,
                                                @PathVariable(required = true) long id) {
        if (sort != null){
            if(!sort.equals("desc") && !sort.equals("asc")){
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY ,"sort not supported");
            }
        }
        if(sort==null && limit==null) {
            return Map.of("ads", service.getUserAds(id));
        } else if(sort==null && limit!=null){
            return Map.of("ads", service.getSortedAds("desc", Integer.parseInt(limit), id));
        } else if(sort!=null && limit==null){
            return Map.of("ads", service.getSortedAds(sort, 10, id));
        }else{
            return Map.of("ads", service.getSortedAds(sort, Integer.parseInt(limit), id));
        }

    }


    @GetMapping("/user/id/ads/{ad_id}")
    public void delete(@PathVariable long ad_id) {
        service.getAdById(ad_id);  }

    @DeleteMapping("/user/{id}/ads/{ad_id}")
    public void delete(@PathVariable long ad_id,@PathVariable long id) {
        service.delete(ad_id);  }

    @PostMapping(value = "/user/{id}/ads/{ad_id}/status", produces="application/json")
    public void postAdStatus(@RequestBody StatusStringDTO status,
                             @PathVariable(required = true) long ad_id) {
        service.updateStatus(ad_id, status.getStatus());
    }

    @GetMapping("/job/match")
    public void findAllMatches(){
        service.matchRequestedRoutesWithVolunteerRoutes();
    }

}