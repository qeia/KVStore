package com.sid.KVStore.Resources;


import com.google.inject.Inject;
import com.sid.KVStore.KVStore;
import com.sid.KVStore.Requests.Add;
import com.sid.KVStore.Requests.Get;
import com.sid.KVStore.Value;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/store")
@Produces(MediaType.APPLICATION_JSON)
public class KVResource {

    @Inject
    private KVStore kvStore;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createKey (Add add) {
        logger.info("adding {}", add);
        kvStore.put(add.getKey(), new Value(new DateTime(), add.getValue()));
        return true;
    }


    @GET
    @Path("/{key}")
    public Get getValueFromKey (@PathParam("key") String key) {
        logger.info("getting {}", key);
        return new Get(kvStore.get(key).getValue());
    }
}
