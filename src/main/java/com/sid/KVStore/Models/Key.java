package com.sid.KVStore.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Key {

    @JsonProperty
    String key;
}
