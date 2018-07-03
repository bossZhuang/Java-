package com.hu.ssm.mapper.cache;

import com.hu.ssm.entity.Seckill;
import redis.clients.jedis.JedisPool;

/**
 * Created by hu on 2018/7/3.
 */
public class RedisDao {

    private final JedisPool jedisPool;

    public RedisDao(String ip,int port){
        System.out.println("是不是常亮修饰后只初始化一次JedisPool");
        jedisPool = new JedisPool(ip,port);
    }

    public Seckill getSeckill(String seckillId){
        return null;
    }

    public String putSeckill(){
        return null;
    }

}
