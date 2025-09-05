package com.tecba.adsitios.controllers;

import com.tecba.adsitios.dtos.Locations;
import com.tecba.adsitios.services.GoogleSiteServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sites")
public class SiteController {

    private final GoogleSiteServices googleSiteServices;

    @GetMapping("/sitios")
    public Locations getLocations(@RequestParam String nameAccount){
        return googleSiteServices.getSites(nameAccount);
    }
}
