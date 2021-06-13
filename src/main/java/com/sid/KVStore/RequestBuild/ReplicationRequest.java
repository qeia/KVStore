package com.sid.KVStore.RequestBuild;

import com.sid.KVStore.Models.Key;
import com.sid.KVStore.Models.Value;

import java.util.List;
import java.util.concurrent.Future;

public interface ReplicationRequest {

    boolean buildAndSendAddRequest(Key key, Value value);
    List<Future<Boolean>> buildAndSendAddRequestAsync(Key key, Value value);
}
