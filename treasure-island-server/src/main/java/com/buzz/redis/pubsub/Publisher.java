package com.buzz.redis.pubsub;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Publisher {
    private Jedis publishJedis;
    private String channel;

    public Publisher(Jedis publishJedis, String channel) {
        this.publishJedis = publishJedis;
        this.channel = channel;
    }

    public void startPublish() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("请输入Message:");
                String line = bufferedReader.readLine();

                if (!"quit".equals(line)) {
                    publishJedis.publish(channel, line);
                } else {
                    break;
                }

            }

        } catch (Exception e) {

        }
    }
}
