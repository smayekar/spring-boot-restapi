package com.ctl.restapi.controller;

import com.ctl.restapi.core.Cluster;
import com.ctl.restapi.service.RestApiService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
public class RestApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    RestApiService restApiService;

    @RequestMapping(value = "/clusters", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Processed Successfully", response = Cluster.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal Error")
    })
    @ResponseBody
    public List<Cluster> getAllClusters(){
        LOGGER.debug("<< Begin getAllClusters >>");
        return restApiService.findAllClusters();
    }

    @RequestMapping(value = "/clusters/id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Processed Successfully", response = Cluster.class),
            @ApiResponse(code = 500, message = "Internal Error")
    })
    @ResponseBody
    public Cluster getClusterById(@RequestParam(value = "id") final String id){
        LOGGER.debug("<< Begin getClusterById >>");
        return restApiService.findClusterById(id);
    }
}
