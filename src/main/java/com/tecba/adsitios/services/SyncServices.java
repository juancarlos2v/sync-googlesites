package com.tecba.adsitios.services;

import com.tecba.adsitios.dtos.Accounts;
import com.tecba.adsitios.entities.Group;
import com.tecba.adsitios.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SyncServices {

    private final GoogleSiteServices googleSiteServices;
    private final GroupRepository groupRepository;
    private final Logger logger = LoggerFactory.getLogger(SyncServices.class);

    @Transactional
    public void syncGroups(String nextPageToken) {
        Accounts accounts = googleSiteServices.getGroups(nextPageToken);


        logger.info(accounts.getNextPageToken());

        for (Accounts.Account account : accounts.getAccounts()) {
            logger.info(account.getAccountName());
            groupRepository.save(
                    Group.builder()
                            .name(account.getName())
                            .accountName(account.getAccountName())
                            .build());
        }

        // por ahora se usa recursividad porque son 3 paginas
        // en caso llegaran a ser muchas mas cambiar por un do while
        String hasToken = accounts.getNextPageToken();
        if (hasToken != null && !accounts.getNextPageToken().isBlank()) {
            syncGroups(hasToken);
        }


    }

    /**@Transactional private void syncAllSites(){


    }**/

}
