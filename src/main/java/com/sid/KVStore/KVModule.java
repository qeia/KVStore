package com.sid.KVStore;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;


public class KVModule extends AbstractModule {

    protected void configure() {

    }

    @Provides
    @Singleton
    public KVStore provideKVStore(){
        return new KVStore();
    }
}
