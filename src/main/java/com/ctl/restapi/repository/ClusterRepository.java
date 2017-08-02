package com.ctl.restapi.repository;

import com.ctl.restapi.core.Cluster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ClusterRepository extends MongoRepository<Cluster, Long> {
    List<Cluster> findAll();

    Cluster findById(String id);
}
