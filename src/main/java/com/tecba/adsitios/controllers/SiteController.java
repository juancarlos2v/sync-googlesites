package com.tecba.adsitios.controllers;

import com.tecba.adsitios.dtos.Locations;
import com.tecba.adsitios.services.GoogleSiteServices;
import com.tecba.adsitios.services.SyncServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sitios")
public class SiteController {

    private final GoogleSiteServices googleSiteServices;
    private final SyncServices syncServices;

    @GetMapping
    public Locations getLocations(@RequestParam String nameAccount){
        return googleSiteServices.getSites(nameAccount);
    }

    @GetMapping("/sync")
    public void syncSites(){
        syncServices.syncAllSites();
    }
}
