package com.tecba.adsitios.controllers;

import com.tecba.adsitios.dtos.Locations;
import com.tecba.adsitios.entities.Site;
import com.tecba.adsitios.repositories.SiteRepository;
import com.tecba.adsitios.services.GoogleSiteServices;
import com.tecba.adsitios.services.SiteServices;
import com.tecba.adsitios.services.SyncServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sitios")
public class SiteController {

    private final SyncServices syncServices;
    private final SiteServices siteServices;

    @GetMapping
    public List<Site> getLocations(@RequestParam String nameAccount){
        return siteServices.getAllSites();
    }

    @GetMapping("/sync")
    public void syncSites(){
        syncServices.syncAllSites();
    }
}
