package com.sid.KVStore.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;


@NoArgsConstructor
public class Add {

    @Getter
    @Setter
    private DateTime timestamp;

    @JsonProperty
    @Getter
    @Setter
    private String key;

    @JsonProperty
    @Getter
    @Setter
    private String value;

    public Add(String key, String value) {
        this.key = key;
        this.value = value;
        this.timestamp = new DateTime();
    }
}
