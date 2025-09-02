package com.tecba.adsitios.repositories;

import com.tecba.adsitios.entities.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository  extends MongoRepository<Group, String> {
}
