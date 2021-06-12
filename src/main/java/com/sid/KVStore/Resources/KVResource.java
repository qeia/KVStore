package com.sid.KVStore.Resources;


import com.google.inject.Inject;
import com.sid.KVStore.KVStore;
import com.sid.KVStore.Requests.Add;
import com.sid.KVStore.Requests.Get;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/store")
@Produces(MediaType.APPLICATION_JSON)
public class KVResource {

    @Inject
    private KVStore kvStore;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createKey (Add add) {
        kvStore.put(add.getKey(), add.getValue());
        return true;
    }


    @GET
    @Path("/{key}")
    public Get getValueFromKey (@PathParam("key") String key) {
        return new Get(kvStore.get(key));
    }
}
