package com.sid.KVStore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sid.KVStore.RequestBuild.ReplicationRequestImpl;
import io.dropwizard.Configuration;

import java.util.List;


public class KVConfiguration extends Configuration {

    public KVConfiguration (){super();}

    @JsonProperty
    public boolean master = true;

    @JsonProperty
    public List<ReplicationRequestImpl.HostAndPort> hostAndPorts;

    @JsonProperty
    public boolean strongConsistency = false;




}
