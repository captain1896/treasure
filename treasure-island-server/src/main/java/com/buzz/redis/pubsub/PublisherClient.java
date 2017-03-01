package com.buzz.redis.pubsub;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class PublisherClient {
    private static final String CHANNEL = "mychannel";
    private static final String HOST = "127.0.0.1";
    private static final Integer PORT = 6379;

    private static final JedisPoolConfig POOL_CONFIG = new JedisPoolConfig();
    private static final JedisPool JEDIS_POOL = new JedisPool(POOL_CONFIG, HOST, PORT, 0);

    public static void main(String[] args) {
        final Jedis subscriberJedis = JEDIS_POOL.getResource();
        final Jedis publisherJedis = JEDIS_POOL.getResource();

        final Subscriber subscriber = new Subscriber();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("subscribe starts:");
                    subscriberJedis.subscribe(subscriber, CHANNEL);
                    System.out.println("subscribe end...");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        //new Publisher(publisherJedis, CHANNEL).startPublish();
        //publisherJedis.close();

        //subscriber.unsubscribe();
        //subscriberJedis.close();
    }
}
