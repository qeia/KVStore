package com.sid.KVStore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class Key {

    @JsonProperty
    String key;
}
