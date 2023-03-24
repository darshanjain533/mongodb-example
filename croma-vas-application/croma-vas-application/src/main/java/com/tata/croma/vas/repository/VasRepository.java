package com.tata.croma.vas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tata.croma.vas.model.Vas;

@Repository
public interface VasRepository extends MongoRepository<Vas, String>{

}
