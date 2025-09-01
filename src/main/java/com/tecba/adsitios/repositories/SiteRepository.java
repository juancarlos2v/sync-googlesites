package com.tecba.adsitios.repositories;

import com.tecba.adsitios.entities.Site;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends MongoRepository<Site,String> {
}
