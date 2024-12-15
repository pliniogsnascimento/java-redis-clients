package org.example;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

// Reference: https://redis.io/docs/latest/develop/clients/lettuce/connect/
public class RedisConnection {

    public void connectBasic() {
        RedisURI uri = RedisURI.Builder
                .redis("localhost", 6379)
                .build();

        RedisClient client = RedisClient.create(uri);
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> commands = connection.sync();

        commands.set("foo", "bar");
        String result = commands.get("foo");
        System.out.println(result);

        connection.close();

        client.shutdown();
    }

    public void connectAsync() {
        RedisURI uri = RedisURI.Builder
                .redis("localhost", 6379)
                .build();

        RedisClient client = RedisClient.create(uri);

        try (StatefulRedisConnection<String, String> conn = client.connect()) {
            RedisAsyncCommands<String, String> asyncCommands = conn.async();

            asyncCommands.set("lorem", "ipsum").get();
            System.out.println(asyncCommands.get("lorem").get());

            Map<String, String> hash = new HashMap<String, String>();
            hash.put("name", "John");
            hash.put("surname", "Smith");
            hash.put("company", "Redis");
            hash.put("age", "29");
            asyncCommands.hset("user-session:123", hash).get();

            System.out.println(asyncCommands.hgetall("user-session:123").get());

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            client.shutdown();
        }
    }
}
