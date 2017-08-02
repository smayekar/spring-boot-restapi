package com.ctl.restapi.core;

import com.google.common.base.MoreObjects;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "clusters")
@Data
public class Cluster {
    @Id
    String id;
    Date lastModificationDate;
    String validationDate;
    String validationVersion;
    String storageServer;
    Long ownerSiteId;
    Long companyId;
    String clusterName;
    String vicIpAddress;
    String clusterId;
    String datacenterLocation;
    String virtualCenterName;
    String virtualCenterId;
    String cloudService;
    String storageSubsystem;
    String validationStatus;
    Date creationDate;
    String creationType;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("lastModificationDate", lastModificationDate)
                .add("validationDate", validationDate)
                .add("validationVersion", validationVersion)
                .add("storageServer", storageServer)
                .add("ownerSiteId", ownerSiteId)
                .add("companyId", companyId)
                .add("clusterName", clusterName)
                .add("vicIpAddress", vicIpAddress)
                .add("clusterId", clusterId)
                .add("datacenterLocation", datacenterLocation)
                .add("virtualCenterName", virtualCenterName)
                .add("virtualCenterId", virtualCenterId)
                .add("cloudService", cloudService)
                .add("storageSubsystem", storageSubsystem)
                .add("validationStatus", validationStatus)
                .add("creationDate", creationDate)
                .add("creationType", creationType)
                .toString();
    }
}
