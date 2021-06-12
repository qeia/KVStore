package com.sid.KVStore;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import io.dropwizard.Configuration;

import java.util.concurrent.*;


public class KVModule extends AbstractModule {

    protected void configure() {

    }

    @Provides
    @Singleton
    public KVStore provideKVStore(KVConfiguration configuration, @Named("ReplicatorThreadPool") ExecutorService executorService){
        return new KVStore(configuration, executorService);
    }

    @Provides
    @Singleton
    @Named("ReplicatorThreadPool")
    public ExecutorService provideReplicatorThreadPool(){
        return new ThreadPoolExecutor(20, 100, 10L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10000));
    }

}
