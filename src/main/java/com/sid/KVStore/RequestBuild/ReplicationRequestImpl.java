package com.sid.KVStore.RequestBuild;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sid.KVStore.Models.Key;
import com.sid.KVStore.Models.Value;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


public class ReplicationRequestImpl implements ReplicationRequest{

    private ExecutorService executorService;
    private List<HostAndPort> replicaHostAndPorts;

    public ReplicationRequestImpl (List<HostAndPort> replicaHostAndPorts, ExecutorService executorService){
        this.executorService = executorService;
        this.replicaHostAndPorts = replicaHostAndPorts;
    }

    private static HttpClient HTTP_CLIENT = HttpClients.createDefault();
    private static ObjectWriter OBJECT_WRITER = new ObjectMapper().writer();

    private static Logger logger = LoggerFactory.getLogger("ReplicationRequestImpl");
    private static boolean BuildAndSendAddRequest(Value value, String url) {

        try {
            String valueAsString = OBJECT_WRITER.writeValueAsString(value);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(valueAsString, "application/json",
                    "UTF-8"));
            logger.info("sending request {} to {}", valueAsString, url);
            HttpResponse httpResponse = HTTP_CLIENT.execute(httpPost);

            return httpResponse.getStatusLine().getStatusCode() == 200;
        } catch (Exception e){
            logger.error("Exception while trying to send request to {}",url,e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean buildAndSendAddRequest(Key key, Value value) {
        List<Future<Boolean>> futures = buildAndSendAddRequestAsync(key, value);
        for (Future<Boolean> future: futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                logger.error("Exception while trying to finish future {}", future, e);
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Future<Boolean>> buildAndSendAddRequestAsync(Key key, Value value) {
        List<String> urls = replicaHostAndPorts.stream().map( hp -> new URIBuilder()
                .setScheme("http")
                .setHost(hp.host)
                .setPort(hp.port)
                .setPathSegments("set", "replica", key.getKey()).toString())
                .collect(Collectors.toList());
        logger.info("urls {}",urls);
        return  urls.stream().
                map(url -> executorService.submit(
                        ()->BuildAndSendAddRequest(value,url)
                )).collect(Collectors.toList());

    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class HostAndPort {
        @JsonProperty
        public String host;
        @JsonProperty
        public int port;
    }
}
