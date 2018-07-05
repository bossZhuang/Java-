package com.hu.ssm.mapper.cache;

import com.hu.ssm.entity.Seckill;
import com.hu.ssm.mapper.SeckilledMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by hu on 2018/7/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class RedisDaoTest{

    private long id = 1001;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SeckilledMapper seckilledMapper;


    @Test
    public void getSeckill() throws Exception {
        Seckill seckill = redisDao.getSeckill(id);
        if (seckill == null){
            seckill = seckilledMapper.queryById(id);
            if (seckill != null){
                String result = redisDao.putSeckill(seckill);
                System.out.println("result:"+result);
                seckill = redisDao.getSeckill(id);
                System.out.println("seckill:"+seckill);
            }
        }
    }
}