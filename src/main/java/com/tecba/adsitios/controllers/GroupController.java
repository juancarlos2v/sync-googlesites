package com.tecba.adsitios.controllers;

import com.tecba.adsitios.entities.Group;
import com.tecba.adsitios.services.GroupsServices;
import com.tecba.adsitios.services.SyncServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grupos")
public class GroupController {

    private final GroupsServices accountServices;
    private final SyncServices syncServices;

    @GetMapping
    public List<Group> getAllGroups() {
        return accountServices.getGroups();
    }

    @GetMapping("/sync")
    public void syncCuentas(){
        syncServices.syncGroups("");
    }
}
