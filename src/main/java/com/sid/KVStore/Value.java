package com.sid.KVStore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.joda.time.DateTime;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Value {

    @JsonProperty
    private Long dateTime;
    @JsonProperty
    private String value;

    public Value (String value){
        this.value = value;
        this.dateTime = new DateTime().getMillis();
    }

    public Value (String value, Long dateTime){
        this.value = value;
        this.dateTime = dateTime;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value1 = (Value) o;
        return value.equals(value1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
