package com.dagong.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * Created by liuchang on 16/5/29.
 */

@Service
public class RedisService {

    private JedisPool jedisPool;
    private boolean isWorking = false;
    @Value("${redis.ip}")
    private String redisIP;
    @Value("${redis.port}")
    private int redisPort = 6379;


    @PostConstruct
    public void init() {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(jedisPoolConfig, redisIP, redisPort);
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

}
