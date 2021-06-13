package com.sid.KVStore;


import com.sid.KVStore.Models.Key;
import com.sid.KVStore.Models.Value;
import com.sid.KVStore.RequestBuild.ReplicationRequest;
import com.sid.KVStore.RequestBuild.ReplicationRequestImpl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;


public class KVStore extends ConcurrentHashMap<Key, Value> {


    private KVConfiguration kvConfiguration;
    private ReplicationRequest replicationRequest;

    public KVStore(KVConfiguration kvConfiguration, ExecutorService executorService) {

        this.kvConfiguration = kvConfiguration;
        if (kvConfiguration.master) {
            replicationRequest = new ReplicationRequestImpl(kvConfiguration.hostAndPorts, executorService);
        }

    }



    @Override
    public Value put(Key key, Value value) {

        //only put if current timestamp comes after existing timestamp
        Value v = merge(key, value, (v1, v2) -> v1.getDateTime() < v2.getDateTime() ? v2 : v1);

        if(kvConfiguration.master) {
            if (kvConfiguration.strongConsistency){
                if (!replicationRequest.buildAndSendAddRequest(key, value)) {
                    throw new RuntimeException("Could not finish the replica request");
                }
            } else {
                replicationRequest.buildAndSendAddRequestAsync(key, value);
            }
        }

        return v;
    }


}
