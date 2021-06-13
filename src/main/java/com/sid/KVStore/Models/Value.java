package com.sid.KVStore.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.joda.time.DateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Value {

    @JsonProperty
    @EqualsAndHashCode.Exclude
    private Long dateTime;

    @JsonProperty
    private String value;

    public Value (String value){
        this.value = value;
        this.dateTime = new DateTime().getMillis();
    }
}
