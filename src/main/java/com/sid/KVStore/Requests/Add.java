package com.sid.KVStore.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sid.KVStore.Key;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Data
public class Add {



    @JsonProperty
    @Getter
    @Setter
    private Key key;

    @JsonProperty
    @Getter
    @Setter
    private String value;

    public Add(Key key, String value) {
        this.key = key;
        this.value = value;
    }
}
