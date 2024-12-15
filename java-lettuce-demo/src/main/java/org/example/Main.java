package org.example;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class Main {
    public static void main(String[] args) {
        RedisConnection redis = new RedisConnection();
        redis.connectAsync();
    }
}