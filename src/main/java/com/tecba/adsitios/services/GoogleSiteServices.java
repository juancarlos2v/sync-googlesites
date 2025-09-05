package com.tecba.adsitios.services;

import com.tecba.adsitios.dtos.Accounts;
import com.tecba.adsitios.dtos.Locations;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class GoogleSiteServices {

    @Value("${google.sites.information}")
    private  String sitesInformation;
    @Value("${google.sites.verification}")
    private  String sitesVerification;
    @Value("${google.sites.account.management}")
    private  String sitesAccountManagement;

    private  String currentToken;
    private  Instant tokenExpiry;

    private final AuthService authService;

    private final Logger log = LoggerFactory.getLogger(GoogleSiteServices.class);

    public Accounts getGroups(String nextPageToken,String token){

        //String token = authService.getToken();
        String url="/accounts?pageToken="+nextPageToken;

        WebClient client = WebClient.builder()
                .baseUrl(sitesAccountManagement)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Accounts.class)
                .block();
    }

    public Locations getSites(String nameAccount, String token){
        //String token = authService.getToken();
        String locationParams="/locations?readMask=storeCode,regularHours,name,languageCode,title,phoneNumbers," +
                "categories,storefrontAddress,websiteUri,regularHours,specialHours,serviceArea,labels,adWordsLocationExtensions," +
                "latlng,openInfo,metadata,profile,relationshipData,moreHours";

        WebClient client = WebClient.builder()
                .baseUrl(sitesInformation)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();

        return client.get()
                .uri(nameAccount+locationParams)
                .retrieve()
                .bodyToMono(Locations.class)
                .block();
    }

}
