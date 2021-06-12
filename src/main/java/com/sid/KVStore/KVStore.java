package com.sid.KVStore;

import java.util.concurrent.ConcurrentHashMap;


public class KVStore extends ConcurrentHashMap<Key, Value> {

    @Override
    public Value put(Key key, Value value) {
        return merge(key, value, (v1, v2) -> v1.getDateTime().isBefore(v2.getDateTime()) ? v2 : v1);
    }


}
