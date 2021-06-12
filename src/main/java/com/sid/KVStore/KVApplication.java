package com.sid.KVStore;

import com.hubspot.dropwizard.guicier.GuiceBundle;
import com.sid.KVStore.Resources.KVResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class KVApplication extends Application<KVConfiguration> {

    public void run(KVConfiguration kvConfiguration, Environment environment) throws Exception {
        environment.jersey().register(KVResource.class);
    }

    @Override
    public void initialize(Bootstrap<KVConfiguration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.defaultBuilder(KVConfiguration.class).modules(new KVModule()).build());
    }

    public static void main(String... args) throws Exception {
        new KVApplication().run(args);
    }
}
