package org.example;

import redis.clients.jedis.UnifiedJedis;

public class Main {
    public static void main(String[] args) {
        try {
            UnifiedJedis jedis = new UnifiedJedis("redis://localhost:6379");
            var status = jedis.set("bike:1", "hello");
            if ("OK".equals(status))
                System.out.println("Data stored");

            System.out.println("Retrieving data from redis");
            System.out.println(jedis.get("bike:1"));

            jedis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}