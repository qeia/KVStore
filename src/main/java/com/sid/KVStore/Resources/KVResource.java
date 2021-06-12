package com.sid.KVStore.Resources;


import com.google.inject.Inject;
import com.sid.KVStore.KVStore;
import com.sid.KVStore.Key;
import com.sid.KVStore.Requests.Add;
import com.sid.KVStore.Requests.Get;
import com.sid.KVStore.Value;
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
        Add add = new Add (new Key(key), new Value(value));
        logger.info("adding {}", add);
        kvStore.put(add.getKey(), add.getValue());
        return true;
    }


    @GET
    @Path("/get/{key}")
    public Get getValueFromKey (@PathParam("key") String key) {
        logger.info("getting {}", key);
        Value v = kvStore.get(new Key(key));
        String value = v == null ? null : v.getValue();
        return new Get(value);
    }
}
