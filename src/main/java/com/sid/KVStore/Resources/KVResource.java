package com.sid.KVStore.Resources;


import com.google.inject.Inject;
import com.sid.KVStore.KVStore;
import com.sid.KVStore.Models.Key;
import com.sid.KVStore.Models.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class KVResource {

    @Inject
    private KVStore kvStore;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    @POST
    @Path("/set/{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createKey (@PathParam("key") String key, String value) {
        kvStore.put(new Key(key), new Value(value));
        return true;
    }

    @POST
    @Path("/set/replica/{key}")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createKey (@PathParam("key") String key, Value value) {
        kvStore.put(new Key(key), value);
        return true;
    }


    @GET
    @Path("/get/{key}")
    public String getValueFromKey (@PathParam("key") String key) {
        logger.info("getting {}", key);
        Value v = kvStore.get(new Key(key));
        return v == null ? null : v.getValue();
    }
}
