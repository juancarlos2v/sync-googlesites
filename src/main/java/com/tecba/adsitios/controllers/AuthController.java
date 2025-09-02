package com.tecba.adsitios.controllers;

import com.tecba.adsitios.dtos.Accounts;
import com.tecba.adsitios.dtos.Locations;
import com.tecba.adsitios.services.AuthService;
import com.tecba.adsitios.services.GoogleSiteServices;
import com.tecba.adsitios.services.SyncServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ads-google")
@RequiredArgsConstructor
public class AuthController {

    private  final GoogleSiteServices  googleSiteServices;
    private final AuthService authService;
    private final SyncServices syncServices;

    @GetMapping("/cuentas")
    public Accounts sincronizarCuentas() {
        return googleSiteServices.getAccounts("");
    }

    @GetMapping("/sitios")
    public Locations getLocations(@RequestParam String nameAccount){
        return googleSiteServices.getLocations(nameAccount);
    }

    @GetMapping("/sync-cuentas")
    public void syncCuentas(){
        syncServices.syncAccounts("");
    }
}
