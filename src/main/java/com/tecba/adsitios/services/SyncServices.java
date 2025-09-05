package com.tecba.adsitios.services;

import com.tecba.adsitios.dtos.Accounts;
import com.tecba.adsitios.dtos.Locations;
import com.tecba.adsitios.entities.Group;
import com.tecba.adsitios.entities.Site;
import com.tecba.adsitios.repositories.GroupRepository;
import com.tecba.adsitios.repositories.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SyncServices {

    private final GoogleSiteServices googleSiteServices;
    private final GroupRepository groupRepository;
    private final Logger logger = LoggerFactory.getLogger(SyncServices.class);
    private final SiteRepository siteRepository;
    private final AuthService authService;

    @Transactional
    public void syncGroups(String nextPageToken) {

        String token = authService.getToken();
        Accounts accounts = googleSiteServices.getGroups(nextPageToken,token);

        if (nextPageToken == null || nextPageToken.isBlank()) {
            groupRepository.deleteAll();
            logger.info("Se borraron todos los registros antes de sincronizar.");
        }

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

    @Transactional
    public void syncAllSites() {

        String token= authService.getToken();
        List<Group> groups = groupRepository.findAll();
        int contadorSitio = 0;
        siteRepository.deleteAll();

        for (Group group : groups) {
            Locations locations = googleSiteServices.getSites("/" + group.getName(),token);
            logger.info("================={}=================", group.getAccountName());

            if (locations.getLocations() != null) {
                for (Locations.Location location : locations.getLocations()) {

                    String address = Optional.ofNullable(location.getStorefrontAddress())
                            .map(Locations.StorefrontAddress::getAddressLines)
                            .filter(list -> !list.isEmpty())
                            .map(list -> list.get(0))
                            .orElse("");

                    String province = Optional.ofNullable(location.getStorefrontAddress())
                            .map(Locations.StorefrontAddress::getLocality)
                            .orElse("");

                    String postalCode = Optional.ofNullable(location.getStorefrontAddress())
                            .map(Locations.StorefrontAddress::getPostalCode)
                            .orElse("");

                    String title = Optional.ofNullable(location.getTitle()).orElse("");


                    logger.info("{} :{}", contadorSitio, location.getTitle());
                    logger.info("    {}", address);
                    contadorSitio++;
                    siteRepository.save(Site.builder()
                            .title(title)
                            .address(address)
                            .province(province)
                            .postalCode(postalCode)
                            .build());
                }
            }
        }

    }

}
