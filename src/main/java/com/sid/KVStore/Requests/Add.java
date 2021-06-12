package com.sid.KVStore.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sid.KVStore.Key;
import com.sid.KVStore.Value;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;


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
    private Value value;


    public Add(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

}
