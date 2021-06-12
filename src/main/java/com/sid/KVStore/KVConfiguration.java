package com.sid.KVStore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;



public class KVConfiguration extends Configuration {

    public KVConfiguration (){super();}


    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    @JsonProperty
    private String logPath;

    @JsonProperty
    public boolean master = true;

    @JsonProperty
    public String replicaHost = null;

    @JsonProperty
    public int replicaPort = 0;

    @JsonProperty
    public boolean strongConsistency = false;

    @JsonProperty
    public boolean hasReplica = false;



}
