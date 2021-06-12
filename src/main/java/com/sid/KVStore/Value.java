package com.sid.KVStore;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@AllArgsConstructor
public class Value {
    private DateTime dateTime;
    private String value;
}
