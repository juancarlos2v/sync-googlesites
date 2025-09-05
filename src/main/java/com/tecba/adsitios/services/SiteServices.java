package com.tecba.adsitios.services;

import com.tecba.adsitios.entities.Site;
import com.tecba.adsitios.repositories.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteServices {

    private final SiteRepository   siteRepository;

    public List<Site> getAllSites(){
        return siteRepository.findAll();
    }

}
