package com.meni.server.rest;

import com.fasterxml.jackson.databind.node.TextNode;
import com.meni.server.model.AdDto;
import com.meni.server.model.Status;
import com.meni.server.model.StatusStringDTO;
import com.meni.server.service.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public Map<String,List<AdDto>> getSortedAds(@RequestParam(required = false) String sort,
                                                @RequestParam(required = false) String limit,
                                                @PathVariable(required = true) long id) {
        if (sort != null){
            if(!sort.equals("desc") && !sort.equals("asc")){
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY ,"sort not supported");
            }
        }
        if(sort==null && limit==null) {
            return Map.of("Ads", service.getUserAds(id));
        } else if(sort==null && limit!=null){
            return Map.of("Ads", service.getSortedAds("desc", Integer.parseInt(limit), id));
        } else if(sort!=null && limit==null){
            return Map.of("Ads", service.getSortedAds(sort, 10, id));
        }else{
            return Map.of("Ads", service.getSortedAds(sort, Integer.parseInt(limit), id));
        }

    }


    @GetMapping("/user/id/ads/{ad_id}")
    public void delete(@PathVariable long ad_id) { service.getAdById(ad_id);  }

    @DeleteMapping("/user/{id}/ads/{ad_id}")
    public void delete(@PathVariable long ad_id,@PathVariable long id) { service.delete(ad_id);  }

    @PostMapping("/user/{id}/ads/{ad_id}/status")
        public void postAdStatus(@RequestBody StatusStringDTO status,
                           @PathVariable(required = true) long ad_id) {
         service.updateStatus(ad_id, status.getStatus());
    }

    @GetMapping("/job/match")
    public void findAllMatches(){
        service.matchRequestedRoutesWithVolunteerRoutes();
    }

}