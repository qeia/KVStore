package com.sid.KVStore.RequestBuild;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sid.KVStore.Key;
import com.sid.KVStore.Requests.Add;
import com.sid.KVStore.Value;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class BuildAddRequest {

    private static HttpClient HTTP_CLIENT = HttpClients.createDefault();
    private static ObjectWriter OBJECT_WRITER = new ObjectMapper().writer();
    private static Logger logger = LoggerFactory.getLogger("BuildAddRequest");
    public static boolean BuildAndSendAddRequest(Add add, String host, int port) throws IOException {

        URIBuilder builder = new URIBuilder()
                .setScheme("http")
                .setHost(host)
                .setPort(port)
                .setPath("/store");
        String valueAsString = OBJECT_WRITER.writeValueAsString(add);
        HttpPost httpPost = new HttpPost(builder.toString());
        httpPost.setEntity(new StringEntity(valueAsString,"application/json",
                "UTF-8"));
        logger.info("sending request {} to {}:{}", valueAsString, host, port);
        HttpResponse httpResponse = HTTP_CLIENT.execute(httpPost);

        return httpResponse.getStatusLine().getStatusCode() == 200;

    }
}
