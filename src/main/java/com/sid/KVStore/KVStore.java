package com.sid.KVStore;


import com.sid.KVStore.RequestBuild.BuildAddRequest;
import com.sid.KVStore.Requests.Add;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


public class KVStore extends ConcurrentHashMap<Key, Value> {


    private KVConfiguration kvConfiguration;

    public KVStore(KVConfiguration kvConfiguration, ExecutorService executorService) {
        this.kvConfiguration = kvConfiguration;
        this.executorService = executorService;
    }


    private ExecutorService executorService;

    Logger logger = LoggerFactory.getLogger("KVStore");

    @SneakyThrows
    @Override
    public Value put(Key key, Value value) {
        Value v = merge(key, value, (v1, v2) -> v1.getDateTime() < v2.getDateTime() ? v2 : v1);
        if(kvConfiguration.master && kvConfiguration.hasReplica) {
            Future<Boolean> result = executorService.submit(() ->

                    BuildAddRequest.BuildAndSendAddRequest(new Add(key, v),
                            kvConfiguration.replicaHost, kvConfiguration.replicaPort)

            );
            if (kvConfiguration.strongConsistency) {
                if (!result.get()) {
                    throw new RuntimeException("Failed to send request to replica");
                }
            }
        }

        return v;
    }


}
