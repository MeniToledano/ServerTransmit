package com.meni.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foos")
public class DeliveryRest {

    @GetMapping(value = "/{id}")
    public String findById(@PathVariable("id") Long id) {
        return "Hello worl " + id;
    }
    @GetMapping(value = "/a/{id}")
    public String meni(@PathVariable("id") Long id) {
        return "Hello worlasdfasfsadf " + id;
    }

}