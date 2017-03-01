package com.buzz.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;

public class RedisDemo {
    public static void main(String[] args) {

        init();
        String key = "test";
        Jedis jedis = new Jedis("localhost");

        //get all the elements sorted from bottom to top
        System.out.println(jedis.zrange(key, 0, -1));


        //We could get the elements with the associated score
        Set<Tuple> elements = jedis.zrevrangeWithScores(key, 0, -1);
        for (Tuple tuple : elements) {
            System.out.println(tuple.getElement() + "-" + tuple.getScore());
        }

        //We can increment a score for a element using ZINCRBY
        System.out.println("Score before zincrby:" + jedis.zscore(key, "Python"));

        //Incrementing the element score
        jedis.zincrby(key, 1, "Python");

        System.out.println("Score after zincrby:" + jedis.zscore(key, "Python"));
        System.out.println("jedis.zcard(key)=" + jedis.zcard(key));
    }

    public static void init() {
        String key = "test";
        Jedis jedis = new Jedis("localhost");

        jedis.zadd(key, 1, "Java");
        jedis.zadd(key, 2, "python");
        jedis.zadd(key, 3, "scala");
        jedis.zadd(key, 4, "c");

        System.out.println(jedis.zrange(key, 0, -1));
    }

}
