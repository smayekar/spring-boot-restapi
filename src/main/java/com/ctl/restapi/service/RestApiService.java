package com.ctl.restapi.service;

import com.ctl.restapi.core.Cluster;
import com.ctl.restapi.repository.ClusterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestApiService {
    private ClusterRepository clusterRepository;

    @Autowired
    public RestApiService(ClusterRepository clusterRepository){
        this.clusterRepository = clusterRepository;
    }

    public List<Cluster> findAllClusters(){
        return clusterRepository.findAll();
    }

    public Cluster findClusterById(final String id){
        return clusterRepository.findById(id);
    }
}
