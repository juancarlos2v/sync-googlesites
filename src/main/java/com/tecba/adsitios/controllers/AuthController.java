package com.tecba.adsitios.controllers;

import com.tecba.adsitios.dtos.Accounts;
import com.tecba.adsitios.dtos.Locations;
import com.tecba.adsitios.services.AuthService;
import com.tecba.adsitios.services.GoogleSiteServices;
import com.tecba.adsitios.services.SyncServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final GoogleSiteServices  googleSiteServices;
    private final AuthService authService;
    private final SyncServices syncServices;



}
