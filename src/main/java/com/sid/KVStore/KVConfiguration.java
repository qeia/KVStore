package com.sid.KVStore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import lombok.*;



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

}
