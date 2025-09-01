package com.tecba.adsitios.controllers;

import com.tecba.adsitios.dtos.Accounts;
import com.tecba.adsitios.dtos.Locations;
import com.tecba.adsitios.services.GoogleSiteServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ads-google")
@RequiredArgsConstructor
public class AuthController {

    private  final GoogleSiteServices  googleSiteServices;

    @GetMapping("/cuentas")
    public Accounts token() {
        //return authService.getToken();
        return googleSiteServices.getAccounts();
    }

    @GetMapping("/sitios")
    public Locations getLocations(@RequestParam String nameAccount){
        return googleSiteServices.getLocations(nameAccount);
    }
}
